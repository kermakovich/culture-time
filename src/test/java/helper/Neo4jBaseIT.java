package helper;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
public class Neo4jBaseIT {

    private final static BaseNeo4jContainer neo4jContainer = new BaseNeo4jContainer();

    @BeforeAll
    static void setUp() {
        neo4jContainer.init();
        neo4jContainer.start();
    }

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", () -> "bolt://localhost:7687");
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", () -> "password");
    }

    @BeforeEach
    void isContainerRunning() {
        Assertions.assertTrue(neo4jContainer.isRunning());
    }

    @AfterAll
    static void destroy() {
        neo4jContainer.stop();
    }

}
