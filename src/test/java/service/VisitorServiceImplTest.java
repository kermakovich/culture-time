package service;

import helper.BaseTest;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import solvd.ermakovich.ct.domain.node.Visitor;
import solvd.ermakovich.ct.repository.VisitorRepository;
import solvd.ermakovich.ct.service.impl.VisitorServiceImpl;

/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class VisitorServiceImplTest extends BaseTest {

    @Mock
    private VisitorRepository visitorRepository;

    @InjectMocks
    private VisitorServiceImpl visitorService;

    @Test
    void createsVisitor() {
        Mockito.doReturn(visitor)
                .when(visitorRepository)
                .save(Mockito.any(Visitor.class));
        Visitor actualVisitor = visitorService.create(visitor);
        MatcherAssert.assertThat(
                "names",
                actualVisitor.getName(),
                Matchers.equalTo("Victor")
        );
        MatcherAssert.assertThat(
                "surnames",
                actualVisitor.getSurname(),
                Matchers.equalTo("Pilipenko")
        );
        Mockito.verify(visitorRepository, Mockito.times(1))
                .save(Mockito.any(Visitor.class));
    }

    @Test
    void makesFriend() {
        Mockito.doReturn(visitorWithFriend)
                .when(visitorRepository)
                .makeFriend(
                        Mockito.any(String.class),
                        Mockito.any(String.class)
                );
        Visitor actualVisitor = visitorService.makeFriend(
                visitor.getId(),
                visitorWithFriend.getId()
        );
        MatcherAssert.assertThat(
                "empty friends list",
                actualVisitor.getFriends(),
                Matchers.not(Matchers.empty())
        );
        Mockito.verify(visitorRepository, Mockito.times(1))
                .makeFriend(
                        Mockito.any(String.class),
                        Mockito.any(String.class)
                );
    }

    @Test
    void getsWayToConnect() {
        final String visitorFrom = "643be014-9f73-417a-91c7-34f0850cfc68";
        final String visitorTo = "7725dfb1-1ad8-4d2d-a271-bbcb8d99ac7d";
        final int expectedCount = 2;
        Mockito.doReturn(visitorList)
                .when(visitorRepository)
                .getFriendsShortestPath(
                        Mockito.any(String.class),
                        Mockito.any(String.class)
                );
        List<Visitor> friendsChain = visitorService
                .getWayToConnect(visitorFrom, visitorTo);
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
                Matchers.hasItems("Pilipenko", "Popov")
        );
    }

}
