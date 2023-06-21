package helper;

import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.utility.MountableFile;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
public class BaseNeo4jContainer extends Neo4jContainer {

    public BaseNeo4jContainer() {
        super("neo4j:5.8.0");
    }

    public void init() {
        this.addFixedExposedPort(7474, 7474);
        this.addFixedExposedPort(7687, 7687);
        this.addEnv("NEO4J_AUTH", "neo4j/password");
        this.withCopyFileToContainer(
                MountableFile.forClasspathResource(
                        "/neo4j/neo4j-init.cypher"
                ),
                "/var/lib/neo4j/db_init/");
        this.withCopyFileToContainer(
                MountableFile.forClasspathResource(
                        "/neo4j/apoc.conf"
                ),
                "/var/lib/neo4j/conf/");
        this.withCopyFileToContainer(
                MountableFile.forClasspathResource(
                        "/neo4j/apoc-5.8.0-extended.jar"
                ),
                "/var/lib/neo4j/plugins/");
        this.withEnv("NEO4J_PLUGINS", "[\"apoc\"]");
    }

}
