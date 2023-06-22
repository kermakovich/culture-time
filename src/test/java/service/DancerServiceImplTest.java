package service;

import helper.BaseTest;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import solvd.ermakovich.ct.domain.exception.EntityDoesNotExistException;
import solvd.ermakovich.ct.domain.node.Dancer;
import solvd.ermakovich.ct.repository.DancerRepository;
import solvd.ermakovich.ct.service.impl.DancerServiceImpl;

/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class DancerServiceImplTest extends BaseTest {

    @Mock
    private DancerRepository dancerRepository;

    @InjectMocks
    private DancerServiceImpl dancerService;

    @Test
    void createsDancer() {
        final Dancer dancer = BaseTest.dancer;
        Mockito.doReturn(dancer)
                .when(dancerRepository)
                .save(Mockito.any(Dancer.class));
        Dancer actualDancer = dancerService.create(dancer);
        Assertions.assertEquals(
                dancer.getSurname(),
                actualDancer.getSurname(),
                "surnames are not equal");
        Assertions.assertEquals(
                dancer.getName(),
                actualDancer.getName(),
                "names are not equal");
        Mockito.verify(dancerRepository, Mockito.times(1))
                .save(Mockito.any(Dancer.class));
    }

    @Test
    void findsById() {
        final String dancerId = "74e5ed85-9343-4441-8862-436bb4d5f07e";
        Mockito.doReturn(Optional.of(BaseTest.dancer))
                .when(dancerRepository)
                .findById(Mockito.any(String.class));
        Dancer actualDancer = dancerService.findById(dancerId);
        Assertions.assertEquals(
                BaseTest.dancer,
                actualDancer,
                "dancers are not equal");
        Mockito.verify(dancerRepository, Mockito.times(1))
                .findById(Mockito.any(String.class));
    }

    @Test
    void throwsErrorWhenDancerDoesNotExist() {
        final String dancerId = "74e5ed85-9343-4441-8862-436bb4d5f07e";
        Mockito.doReturn(Optional.empty())
                .when(dancerRepository)
                .findById(Mockito.any(String.class));
        Assertions.assertThrows(
                EntityDoesNotExistException.class,
                () -> dancerService.findById(dancerId),
                "exceptions is not thrown"
                );
        Mockito.verify(dancerRepository, Mockito.times(1))
                .findById(Mockito.any(String.class));
    }

    @Test
    void getsPerformanceCount() {
        final String dancerId = "74e5ed85-9343-4441-8862-436bb4d5f07e";
        final Long performancesCount = 2L;
        Mockito.doReturn(performancesCount)
                .when(dancerRepository)
                .getPerformancesCount(Mockito.any(String.class));
        Long actualCount = dancerService.getPerformancesCount(dancerId);
        Assertions.assertEquals(
                performancesCount,
                actualCount,
                "values are not equal");
        Mockito.verify(dancerRepository, Mockito.times(1))
                .getPerformancesCount(Mockito.any(String.class));
    }

    @Test
    void deletesDancer() {
        final String dancerId = "74e5ed85-9343-4441-8862-436bb4d5f07e";
        Mockito.doNothing()
                .when(dancerRepository)
                .deleteById(Mockito.any(String.class));
        dancerService.delete(dancerId);
        Mockito.verify(dancerRepository, Mockito.times(1))
                .deleteById(Mockito.any(String.class));
    }

}
