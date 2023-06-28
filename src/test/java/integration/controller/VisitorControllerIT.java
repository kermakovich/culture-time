package integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.BaseTest;
import helper.Neo4jBaseIT;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import solvd.ermakovich.ct.AppInitializer;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
@AutoConfigureMockMvc
@ContextConfiguration(classes = AppInitializer.class)
@ActiveProfiles("test")
final class VisitorControllerIT extends Neo4jBaseIT {

    private static final String BASE_URL = "/api/v1/visitors";

    @Autowired
    private MockMvc mvc;

    @Test
    void createsVisitor() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper()
                                .writeValueAsString(BaseTest.visitor)
                        ))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath(
                                "$['name']",
                                Is.is("Victor")
                        )
                )
                .andExpect(MockMvcResultMatchers.jsonPath(
                                "$['surname']",
                                Is.is("Pilipenko")
                        )
                );
    }

    @Test
    void makesFriend() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/{from}",
                                "b01dd126-3f76-8bdd-a00f-1b86d3d6ae9e")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("857d1c79-3f76-40d5-a00f-8c785595994b"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath(
                                "$['name']",
                                Is.is("sergey")
                        )
                )
                .andExpect(MockMvcResultMatchers.jsonPath(
                                "$['surname']",
                                Is.is("lopeko")
                        )
                );
    }

    @Test
    void getsRecommendations() throws Exception {
        final String visitorId = "857d1c79-3f76-40d5-a00f-8c785595994b";
        mvc.perform(MockMvcRequestBuilders.get(
                                BASE_URL + "/{visitorId}/recommendations",
                                visitorId
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$[*]['title']",
                                Matchers.hasItems(
                                        "Romeo and Juliet", "Swan Lake")
                        )
                );
    }

}
