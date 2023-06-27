package integration.neo4j;

import helper.Neo4jBaseIT;
import java.time.LocalDate;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
final class PerformanceRepositoryIT extends Neo4jBaseIT {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Test
    void verifiesRecommendations() {
        final String visitorId = "857d1c79-3f76-40d5-a00f-8c785595994b";
        List<PerformanceProjection> performances =
                performanceRepository
                        .getRecommendationsBasedOnFriendsLikes(
                                visitorId
                        );
        MatcherAssert.assertThat(
                "empty list",
                performances,
                Matchers.not(Matchers.empty())
        );
        MatcherAssert.assertThat(
                "list does not contain expected performance",
                performances.stream()
                        .map(PerformanceProjection::getTitle)
                        .toList(),
                Matchers.hasItem("Romeo and Juliet")
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
        MatcherAssert.assertThat(
                "dancer is not added",
                expectedPerformance.getDancers(),
                Matchers.not(Matchers.empty())
        );
    }

}
