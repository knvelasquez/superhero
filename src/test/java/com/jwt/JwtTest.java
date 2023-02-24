package com.jwt;

import com.jwt.api.JwtApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = com.jwt.JwtTest.class)
public class JwtTest {

    static JwtApi jwtApi;

    @BeforeEach
    void beforeEach() {
        jwtApi = mock(JwtApi.class);
    }


    @Test
    @DisplayName("Create a jwt token with provide idUser and privileges params should return ok result")
    public void createAJwtTokenWithProvideIdUserAdnPrivilegesParamsShouldReturnOkResult() {
        //Arrange
        final int idUser = 1;
        final List<String> roleAdminPrivilege = new ArrayList<>();
        roleAdminPrivilege.add("ROLE_ADMIN");
        when(jwtApi.create(Mock.idUser1, Mock.roleAdminPrivilege())).thenReturn(Mock.jwtUser1);

        //Act
        String jwt = jwtApi.create(idUser, roleAdminPrivilege);

        //Assert
        assertNotNull(jwt);
        assertEquals(jwt, Mock.jwtUser1);
    }
}
