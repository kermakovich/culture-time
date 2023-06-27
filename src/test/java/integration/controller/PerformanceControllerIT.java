package integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import solvd.ermakovich.ct.AppInitializer;
import solvd.ermakovich.ct.repository.PerformanceRepository;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
@AutoConfigureMockMvc
@ContextConfiguration(classes = AppInitializer.class)
@ActiveProfiles("test")
final class PerformanceControllerIT extends Neo4jBaseIT {

    private static final String BASE_URL = "/api/v1/performances";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Test
    void createsPerformance() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                new ObjectMapper().writeValueAsString(BaseTest.performance)
                        )
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$['title']",
                                Is.is("Cinderella")
                        )
                );
    }

    @Test
    void findsById() throws Exception {
        final String performanceId = "d001a193-e232-4c1b-0086-1b86d3d6ae9e";
        mvc.perform(
                        MockMvcRequestBuilders.get(
                                        BASE_URL + "/{performanceId}",
                                        performanceId
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$['title']",
                                Is.is("Cinderella"))
                );
    }

    @Test
    void addsDancer() throws Exception {
        final String performanceId = "d001a193-e232-4c1b-0086-1b86d3d6ae9e";
        var mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mvc.perform(
                MockMvcRequestBuilders.post(
                        BASE_URL + "/{performanceId}/dancers",
                                performanceId
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(
                                BaseTest.dancerInPerformance
                        ))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                );
        MatcherAssert.assertThat(
                "dancer in performance",
                performanceRepository.findById(performanceId)
                        .orElseThrow()
                        .getDancers(),
                Matchers.not(Matchers.empty())
        );
    }

    @Test
    void deletesById() throws Exception {
        final String performanceId = "d001a193-e232-4c1b-0086-1b86d3d6ae9e";
        mvc.perform(
                MockMvcRequestBuilders.delete(
                        BASE_URL + "/{performanceId}",
                                performanceId
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        MatcherAssert.assertThat(
                "deletion failed",
                performanceRepository.existsById(performanceId),
                Matchers.is(false)
                );
    }



}
