package com.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = com.user.UserTest.class)
public class UserTest {

    static UserApi userApi;

    @BeforeEach
    void beforeEach() {
        userApi = mock(UserApi.class);
    }

    @Test
    @DisplayName("Get all privileges with a provide idUser params should return ok result")
    public void getAllPrivilegesWithAProvideIdUserParamsShouldReturnOkResult() {
        //Assert
        when(userApi.getAllPrivileges(1)).thenReturn(Mock.getPrivilegeAdmin());
        when(userApi.getAllPrivileges(2)).thenReturn(Mock.getPrivilegeConsult());
        when(userApi.getAllPrivileges(3)).thenReturn(Mock.getPrivilegeCreate());
        when(userApi.getAllPrivileges(4)).thenReturn(Mock.getPrivilegeUpdate());
        when(userApi.getAllPrivileges(5)).thenReturn(Mock.getPrivilegeDelete());

        //Act
        final int idUser = 1;
        final List<String> privileges = userApi.getAllPrivileges(idUser);

        final int idUser2 = 2;
        final List<String> privileges2 = userApi.getAllPrivileges(idUser2);

        final int idUser3 = 3;
        final List<String> privileges3 = userApi.getAllPrivileges(idUser3);

        final int idUser4 = 4;
        final List<String> privileges4 = userApi.getAllPrivileges(idUser4);

        final int idUser5 = 5;
        final List<String> privileges5 = userApi.getAllPrivileges(idUser5);


        //Assert
        assertNotNull(privileges);
        assertTrue(privileges.size() > 0);

        assertNotNull(privileges2);
        assertTrue(privileges2.size() > 0);

        assertNotNull(privileges3);
        assertTrue(privileges3.size() > 0);

        assertNotNull(privileges4);
        assertTrue(privileges4.size() > 0);

        assertNotNull(privileges5);
        assertTrue(privileges5.size() > 0);
    }
}
