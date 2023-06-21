package integration;

import helper.Neo4jBaseIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import solvd.ermakovich.ct.repository.DancerRepository;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
@DirtiesContext
final class DancerRepositoryIT extends Neo4jBaseIT {

    @Autowired
    DancerRepository dancerRepository;

    @Test
    void verifiesRightPerformancesCount() {
        final String dancerId = "b8bc995c-e232-4787-a0f5-1b86d3d6ae9e";
        Long actualCount = dancerRepository
                .getPerformancesCount(dancerId);
        Assertions.assertEquals(2L, actualCount, "wrong count");
    }

}
