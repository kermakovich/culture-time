package integration.neo4j;

import helper.Neo4jBaseIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import solvd.ermakovich.ct.domain.node.Visitor;
import solvd.ermakovich.ct.repository.VisitorRepository;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
final class VisitorRepositoryIT extends Neo4jBaseIT {

    @Autowired
    private VisitorRepository visitorRepository;

    @Test
    void makesFriend() {
        final String from = "b01dd126-3f76-8bdd-a00f-1b86d3d6ae9e";
        final String to = "857d1c79-3f76-40d5-a00f-8c785595994b";
        Visitor actualVisitor = visitorRepository.makeFriend(from, to);
        Assertions.assertFalse(
                actualVisitor.getFriends().isEmpty(),
                "error during adding relation \"FRIEND\""
        );
    }

}
