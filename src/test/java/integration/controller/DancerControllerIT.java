package integration.controller;

import helper.BaseTest;
import helper.Neo4jBaseIT;
import org.hamcrest.MatcherAssert;
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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import solvd.ermakovich.ct.AppInitializer;
import solvd.ermakovich.ct.repository.DancerRepository;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
@AutoConfigureMockMvc
@ContextConfiguration(classes = AppInitializer.class)
@ActiveProfiles("test")
final class DancerControllerIT extends Neo4jBaseIT {

    private static final String BASE_URL = "/api/v1/dancers";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DancerRepository dancerRepository;

    @Test
    void findsById() throws Exception {
        final String dancerId = "b8bc995c-e232-4787-a0f5-1b86d3d6ae9e";
        mvc.perform(MockMvcRequestBuilders.get(
                                BASE_URL + "/{dancerId}",
                                dancerId
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$['name']",
                                Is.is("vasiliy"))
                );
    }

    @Test
    void createsDancer() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper()
                                .writeValueAsString(BaseTest.dancer)
                        ))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$['name']",
                                Is.is("alex")
                        )
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$['surname']",
                                Is.is("polonov")
                        )
                );
    }

    @Test
    void getsPerformancesCount() throws Exception {
        final Long expectedCount = 2L;
        final String dancerId = "b8bc995c-e232-4787-a0f5-1b86d3d6ae9e";
        mvc.perform(MockMvcRequestBuilders.get(
                BASE_URL + "/{dancerId}/performances/count",
                                dancerId
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(expectedCount.toString()));
        MatcherAssert.assertThat(
                "performances count",
                dancerRepository.getPerformancesCount(dancerId),
                Matchers.equalTo(expectedCount)
        );
    }

    @Test
    void deletesById() throws Exception {
        final String dancerId = "2a404590-f9e7-463e-a11a-1fdac3a1d1c4";
        mvc.perform(MockMvcRequestBuilders.delete(
                BASE_URL + "/{dancerId}",
                                dancerId
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        MatcherAssert.assertThat(
                "deletion failed",
                dancerRepository.existsById(dancerId),
                Matchers.is(false)
        );
    }

}
