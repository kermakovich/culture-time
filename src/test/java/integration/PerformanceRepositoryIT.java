package integration;

import helper.Neo4jBaseIT;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import solvd.ermakovich.ct.domain.node.Performance;
import solvd.ermakovich.ct.repository.PerformanceRepository;
import solvd.ermakovich.ct.repository.projection.PerformanceProjection;
import solvd.ermakovich.ct.web.dto.DancerInPerformance;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
final class PerformanceRepositoryIT extends Neo4jBaseIT {

    @Autowired
    PerformanceRepository performanceRepository;

    @Test
    void verifiesRecommendations() {
        final String visitorId = "857d1c79-3f76-40d5-a00f-8c785595994b";
        final Long expectedListSize = 2L;
        List<PerformanceProjection> performances =
                performanceRepository
                        .getRecommendationsBasedOnFriendsLikes(
                                visitorId
                        );
        Assertions.assertEquals(expectedListSize, performances.size());
        Assertions.assertTrue(
                performances.stream()
                        .anyMatch(
                                p -> "Swan Lake".equals(p.getTitle())
                        ),
                "list does not contain expected performance"
        );
    }

    @Test
    void addsDancerToPerformance() {
        final String dancerId = "b8b4355c-ea3b-458c-a0f5-1b86d3d6ae9e";
        final String performanceId = "b01dd126-0086-4c22-98a8-6a9b71c634b2";
        Performance expectedPerformance =
                performanceRepository
                        .addDancer(
                                new DancerInPerformance(
                                        dancerId,
                                        LocalDate.now()),
                                performanceId
                        );
        Assertions.assertFalse(
                expectedPerformance.getDancers().isEmpty(),
                "dancer is not added");
    }

}
