package helper;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.utility.MountableFile;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
@SpringBootTest
public class Neo4jBaseIT extends BaseTest {

    @Autowired
    private Neo4jClient neo4jClient;

    private final static Neo4jContainer neo4jContainer =
            (Neo4jContainer) new Neo4jContainer("neo4j:5.8.0")
                    .withCopyFileToContainer(
                            MountableFile.forClasspathResource(
                                    "/neo4j/neo4j-init.cypher"
                            ),
                            "/var/lib/neo4j/db_init/"
                    )
                    .withCopyFileToContainer(
                            MountableFile.forClasspathResource(
                                    "/neo4j/apoc.conf"
                            ),
                            "/var/lib/neo4j/conf/"
                    )
                    .withCopyFileToContainer(
                            MountableFile.forClasspathResource(
                                    "/neo4j/apoc-5.8.0-extended.jar"
                            ),
                            "/var/lib/neo4j/plugins/"
                    )
                    .withEnv("NEO4J_PLUGINS", "[\"apoc\"]")
                    .withEnv("NEO4J_AUTH", "neo4j/password");

    @BeforeAll
    static void setUp() {
        neo4jContainer.start();
    }

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", () -> "password");
    }

    @BeforeEach
    void isContainerRunning() {
        Assertions.assertTrue(neo4jContainer.isRunning());
    }

    @BeforeEach
    void resetDatabase() {
        neo4jClient.query("MATCH (n) DETACH DELETE n")
                .run();
        neo4jClient.query("CALL apoc.cypher.runFile(\"file:////var/lib/neo4j/db_init/neo4j-init.cypher\")")
                .run();
    }

    @AfterAll
    static void destroy() {
        neo4jContainer.stop();
    }

}
