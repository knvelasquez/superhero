package superhero.integration;

import com.superhero.lab.jwt.model.JwtRequestModel;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtRestControllerMockMvc {

    private final MockMvc mvc;

    public JwtRestControllerMockMvc(WebApplicationContext context, FilterChainProxy springSecurityFilterChain) {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    String createJwtFromId(int idUser) throws Exception {
        //Arrange
        final String url = "/jwt";
        final MockHttpServletRequestBuilder httpMock = MockMvcRequestBuilders.post("/jwt");
        httpMock.contentType("application/json").accept("*/*");
        final JwtRequestModel request = new JwtRequestModel(idUser);
        final String body = request.toString();

        //Act
        httpMock.content(body);
        final String json = mvc
                .perform(httpMock)
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertTrue(!json.equals(""));

        return json;
    }
}
