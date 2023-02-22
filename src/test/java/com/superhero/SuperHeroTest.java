package com.superhero;

import com.superhero.api.SuperHeroApi;
import com.superhero.model.SuperHeroModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SuperHeroTest {

    static SuperHeroApi superHeroApi;

    @BeforeEach
    void beforeEach() {
        superHeroApi = mock(SuperHeroApi.class);
    }

    @Test
    @DisplayName("Get all super heroes should return list with ok result")
    public void getAllSuperHeroesShouldReturnListWithOkResult() {
        //Arrange
        when(superHeroApi.getAll()).thenReturn(Mock.AllSuperHeroes());

        //Act
        List<SuperHeroModel> listAllSuperHeroResult = superHeroApi.getAll();

        //Assert
        assertTrue(listAllSuperHeroResult.size() > 0);
    }

    @Test
    @DisplayName("Get a super hero information by unique id should return ok result")
    public void getASuperHeroInformationByUniqueIdShouldReturnOkResult() {
        //Arrange
        when(superHeroApi.getByUniqueId(1L)).thenReturn(Mock.superMan);
        when(superHeroApi.getByUniqueId(2L)).thenReturn(Mock.batMan);
        when(superHeroApi.getByUniqueId(3L)).thenReturn(Mock.spiderMan);

        //Act
        SuperHeroModel superHeroResult1 = superHeroApi.getByUniqueId(1L);
        SuperHeroModel superHeroResult2 = superHeroApi.getByUniqueId(2L);
        SuperHeroModel superHeroResult3 = superHeroApi.getByUniqueId(3L);

        //Assert
        assertNotNull(superHeroResult1);
        assertEquals("SuperMan", superHeroResult1.getName());

        assertNotNull(superHeroResult2);
        assertEquals("BatMan", superHeroResult2.getName());

        assertNotNull(superHeroResult3);
        assertEquals("SpiderMan", superHeroResult3.getName());
    }

    @Test
    @DisplayName("Get all super heroes information by containing name should return ok result")
    public void getAllSuperHeroesInformationByContainingNameShouldReturnOkResult() {
        //Arrange
        when(superHeroApi.getAllByContainingName("man")).thenReturn(Mock.listSuperHeroesContainManInName());

        //Act
        List<SuperHeroModel> superHeroesResult = superHeroApi.getAllByContainingName("man");

        //Assert
        superHeroesResult.forEach(
                (superHero) -> assertTrue(superHero.getName().toLowerCase().contains("man"))
        );
    }

    @Test
    @DisplayName("Create a super hero by provide information should return ok result")
    public void createASuperHeroByProvideInformationShouldReturnOkResult(){
        //Arrange
        when(superHeroApi.createOrUpdate(any())).thenReturn(Mock.createdSuperHero());

        //Act
        SuperHeroModel createdSuperHeroModel = new SuperHeroModel("createdSuperHeroName");
        SuperHeroModel superHeroModelResult = superHeroApi.createOrUpdate(createdSuperHeroModel);

        //Assert
        assertNotNull(superHeroModelResult);
        assertNotEquals(0, superHeroModelResult.getId());
        assertEquals("createdSuperHeroName", superHeroModelResult.getName());
    }
}
