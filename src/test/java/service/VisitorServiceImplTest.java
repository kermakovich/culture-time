package service;

import helper.BaseTest;
import org.junit.jupiter.api.Assertions;
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
        Assertions.assertEquals(
                visitor.getName(),
                actualVisitor.getName(),
                "names are not equal");
        Assertions.assertEquals(
                visitor.getSurname(),
                actualVisitor.getSurname(),
                "surnames are not equal");
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
        Assertions.assertFalse(
                actualVisitor.getFriends().isEmpty(),
                "friends list is empty");
        Mockito.verify(visitorRepository, Mockito.times(1))
                .makeFriend(
                        Mockito.any(String.class),
                        Mockito.any(String.class)
                );
    }

}
