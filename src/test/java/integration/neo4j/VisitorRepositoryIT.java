package integration.neo4j;

import helper.Neo4jBaseIT;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
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
        MatcherAssert.assertThat(
                "error during adding relation \"FRIEND\"",
                actualVisitor.getFriends(),
                Matchers.not(Matchers.empty())
        );
    }

    @Test
    void getsFriendsPath() {
        final String visitorFrom = "643be014-9f73-417a-91c7-34f0850cfc68";
        final String visitorTo = "7725dfb1-1ad8-4d2d-a271-bbcb8d99ac7d";
        final int expectedCount = 3;
        List<Visitor> friendsChain = visitorRepository
                .getFriendsShortestPath(visitorFrom, visitorTo);
        MatcherAssert.assertThat(
                "wrong size list",
                friendsChain.size(),
                Matchers.equalTo(expectedCount)
        );
        MatcherAssert.assertThat(
                "wrong friends list",
                friendsChain.stream()
                        .map(Visitor::getSurname)
                        .toList(),
                Matchers.hasItems("lopenko", "pilemko", "popov")
        );
    }

}
