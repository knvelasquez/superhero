package com.superhero;

import com.superhero.api.SuperHeroApi;
import com.superhero.model.SuperHeroModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
