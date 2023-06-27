package integration.neo4j;

import helper.Neo4jBaseIT;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import solvd.ermakovich.ct.repository.DancerRepository;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
final class DancerRepositoryIT extends Neo4jBaseIT {

    @Autowired
    private DancerRepository dancerRepository;

    @Test
    void verifiesRightPerformancesCount() {
        final Long expectedCount = 2L;
        final String dancerId = "b8bc995c-e232-4787-a0f5-1b86d3d6ae9e";
        Long actualCount = dancerRepository
                .getPerformancesCount(dancerId);
        MatcherAssert.assertThat(
                "performances count",
                actualCount,
                Matchers.equalTo(expectedCount)
        );
    }

}
