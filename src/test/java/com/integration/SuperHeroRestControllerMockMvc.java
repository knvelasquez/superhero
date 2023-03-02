package com.integration;

import com.superhero.model.SuperHeroModel;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SuperHeroRestControllerMockMvc {

    private final MockMvc mvc;
    private MockHttpServletRequestBuilder httpMock;

    public SuperHeroRestControllerMockMvc(WebApplicationContext context, FilterChainProxy springSecurityFilterChain) {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    String getAll(String json) throws Exception {
        //Arrange
        final String url = "/superhero";
        httpMock = MockMvcRequestBuilders.get(url);

        //Act
        final String response = performRequest(json);

        //Assert
        assertFromResponse(response);

        return response;

    }

    String getByUniqueId(String json, int idSuperHero) throws Exception {
        //Arrange
        final String id = String.valueOf(idSuperHero);
        final StringBuilder url = new StringBuilder("/superhero/");
        url.append(id);
        httpMock = MockMvcRequestBuilders.get(url.toString());

        //Act
        final String response = performRequest(json);

        //Assert
        assertFromResponse(response);

        return response;
    }

    String getByContainName(String json, String name) throws Exception {
        //Arrange
        final StringBuilder url = new StringBuilder("/superhero/contain/");
        url.append(name);
        httpMock = MockMvcRequestBuilders.get(url.toString());

        //Act
        final String response = performRequest(json);

        //Assert
        assertFromResponse(response);

        return response;
    }

    String create(String json, SuperHeroModel superHero) throws Exception {
        //Arrange
        final String url = "/superhero";
        httpMock = MockMvcRequestBuilders.post(url);
        httpMock.content(superHero.toString());

        //Act
        final String response = performRequest(json);

        //Assert
        assertFromResponse(response);

        return response;
    }

    String update(String json, SuperHeroModel superHero) throws Exception {
        //Arrange
        final String url = "/superhero";
        httpMock = MockMvcRequestBuilders.put(url);
        httpMock.content(superHero.toString());

        //Act
        final String response = performRequest(json);

        //Assert
        assertFromResponse(response);

        return response;
    }

    String delete(String json, Long idUser) throws Exception {
        //Arrange
        final StringBuilder url = new StringBuilder("/superhero/");
        url.append(String.valueOf(idUser));
        httpMock = MockMvcRequestBuilders.delete(url.toString());

        //Act
        final String response = performRequest(json);

        //Assert
        assertFromResponse(response);

        return response;
    }

    private String performRequest(String json) throws Exception {
        httpMock.contentType("application/json").accept("*/*");
        final StringBuilder authorization = new StringBuilder("Bearer ");
        authorization.append(json);
        httpMock.header("Authorization", authorization.toString());
        return mvc
                .perform(httpMock)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private void assertFromResponse(String response) {
        assertNotNull(response);
        assertNotEquals("Access Denied", response);
    }
}
