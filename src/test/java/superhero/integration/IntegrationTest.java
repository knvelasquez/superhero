package superhero.integration;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import com.superhero.lab.superhero.model.SuperHeroModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = ApplicationConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    private final JwtRestControllerMockMvc jwtRestControllerMockMvc;
    private final SuperHeroRestControllerMockMvc superHeroRestControllerMockMvc;
    private final Gson gson = new Gson();

    @Autowired
    public IntegrationTest(WebApplicationContext context, FilterChainProxy springSecurityFilterChain) {
        jwtRestControllerMockMvc = new JwtRestControllerMockMvc(context, springSecurityFilterChain);
        superHeroRestControllerMockMvc = new SuperHeroRestControllerMockMvc(context, springSecurityFilterChain);
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    @Sql(statements = "INSERT INTO `user_privilege`(id_user, id_privilege) VALUES (1025, 'ROLE_CONSULT')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('SuperMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('BatMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('SpiderMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('CaptainAmerica')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('Thor')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('Hulk')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('IronMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('Flash')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Create a jwt with CONSULT or ADMIN privilege and Get all super heroes with that jwt created then should return list with ok result")
    public void createAJwtWitCorrectPrivilegeAndGetAllSuperHeroesWithThatJwtCreatedThenShouldReturnListWithOkResult() throws Exception {
        //Arrange
        final Type listType = new TypeToken<List<SuperHeroModel>>() {
        }.getType();
        final int idUser = 1025;

        //Act
        final String json = jwtRestControllerMockMvc.createJwtFromId(idUser);
        final String response = superHeroRestControllerMockMvc.getAll(json);
        final List<SuperHeroModel> superHeroes = gson.fromJson(response, listType);

        //Assert
        assertTrue(superHeroes.size() > 0);

    }


    @Test
    @Sql(statements = "INSERT INTO `user_privilege`(id_user, id_privilege) VALUES (1025, 'ROLE_CONSULT')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `user_privilege`(id_user, id_privilege) VALUES (1050, 'ROLE_ADMIN')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `user_privilege`(id_user, id_privilege) VALUES (1025, 'ROLE_CONSULT')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('SuperMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('BatMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('SpiderMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('CaptainAmerica')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('Thor')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('Hulk')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('IronMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('Flash')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Create a jwt with CONSULT or ADMIN privilege and Get a Super Hero by unique id with that jwt create then should return ok result")
    public void createAJWtWithCorrectPrivilegeAndGetASuperHeroByUniqueIdWithThatJwtCreatedThenShouldReturnOkResult() throws Exception {
        //Arrange
        final int idUser = 1025;
        final int idSuperHero = 5;
        final Type type = new TypeToken<SuperHeroModel>() {
        }.getType();

        //Act
        final String json = jwtRestControllerMockMvc.createJwtFromId(idUser);
        final String response = superHeroRestControllerMockMvc.getByUniqueId(json, idSuperHero);
        SuperHeroModel superHeroes = gson.fromJson(response, type);

        //Assert
        assertNotNull(superHeroes);
    }


    @Test
    @Sql(statements = "INSERT INTO `user_privilege`(id_user, id_privilege) VALUES (1025, 'ROLE_CONSULT')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('SuperMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('BatMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('SpiderMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('CaptainAmerica')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('Thor')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('Hulk')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('IronMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('Flash')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Create a jwt with CONSULT or ADMIN privilege and Get super heroes by contain name with that jwt created then should return list with ok result")
    public void createAJwtWitCorrectPrivilegeAndGetSuperHeroesByContainNameWithThatJwtCreatedThenShouldReturnListWithOkResult() throws Exception {
        //Arrange
        final int idUser = 1025;
        final String name = "man";
        final Type listType = new TypeToken<List<SuperHeroModel>>() {
        }.getType();

        //Act
        final String json = jwtRestControllerMockMvc.createJwtFromId(idUser);
        final String response = superHeroRestControllerMockMvc.getByContainName(json, name);

        List<SuperHeroModel> superHeroes = gson.fromJson(response, listType);

        //Assert
        assertTrue(superHeroes.size() > 0);
    }

    @Test
    @Sql(statements = "INSERT INTO `user_privilege`(id_user, id_privilege) VALUES (1030, 'ROLE_CREATE')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Create a jwt with CREATE or ADMIN privilege and create a super hero with that jwt created then should return ok result")
    public void createAJwtWithCorrectPrivilegeAndCreateSuperHeroWithThatJwtCreatedThenShouldReturnOkResult() throws Exception {
        //Arrange
        final int idUser = 1030;
        final SuperHeroModel hulk = new SuperHeroModel("Hulk");
        final Type Type = new TypeToken<SuperHeroModel>() {
        }.getType();

        //Act
        final String json = jwtRestControllerMockMvc.createJwtFromId(idUser);
        final String response = superHeroRestControllerMockMvc.create(json, hulk);
        final SuperHeroModel superHero = gson.fromJson(response, Type);

        //Assert
        assertNotNull(superHero.getId());
        assertNotNull(superHero.getName());

    }

    @Test
    @Sql(statements = "INSERT INTO `user_privilege`(id_user, id_privilege) VALUES (1040, 'ROLE_UPDATE')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('SuperMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Create a jwt with UPDATE or ADMIN privilege and update a super hero with that jwt created then should return ok result")
    public void createAJwtWithCorrectPrivilegeAndUpdateSuperHeroWithThatJwtCreatedThenShouldReturnOkResult() throws Exception {
        //Arrange
        final int idUser = 1040;
        final Long idSuperHero = 4L;
        final SuperHeroModel superManUpdated = new SuperHeroModel(idSuperHero, "SuperManUpdated");
        final Type Type = new TypeToken<SuperHeroModel>() {
        }.getType();

        //Act
        final String json = jwtRestControllerMockMvc.createJwtFromId(idUser);
        final String response = superHeroRestControllerMockMvc.update(json, superManUpdated);
        final SuperHeroModel superHero = gson.fromJson(response, Type);

        //Assert
        assertNotNull(superHero.getId());
        assertNotNull(superHero.getName());

    }

    @Test
    @Sql(statements = "INSERT INTO `user_privilege`(id_user, id_privilege) VALUES (1050, 'ROLE_ADMIN')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `super_hero` (name) VALUES ('BatMan')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Create a jwt with DELETE or ADMIN privilege and delete a super hero with that jwt created then should return ok result")
    public void createAJwtWithCorrectPrivilegeAndDeleteSuperHeroWithThatJwtCreatedThenShouldReturnOkResult() throws Exception {
        //Arrange
        final int idUser = 1050;
        final Long idSuperHero = 4L;

        //Act
        final String json = jwtRestControllerMockMvc.createJwtFromId(idUser);

        //Assert
        assertDoesNotThrow(() -> superHeroRestControllerMockMvc.delete(json, idSuperHero));
    }
}
