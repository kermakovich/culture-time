package integration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
@Configuration
@EnableAutoConfiguration
@ComponentScan("solvd.ermakovich.ct.repository")
@EnableNeo4jRepositories("solvd.ermakovich.ct.repository")
public class TestConfig { }
