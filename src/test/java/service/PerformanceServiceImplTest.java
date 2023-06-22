package service;

import helper.BaseTest;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import solvd.ermakovich.ct.domain.exception.EntityDoesNotExistException;
import solvd.ermakovich.ct.domain.node.Performance;
import solvd.ermakovich.ct.repository.PerformanceRepository;
import solvd.ermakovich.ct.repository.projection.PerformanceProjection;
import solvd.ermakovich.ct.service.impl.PerformanceServiceImpl;
import solvd.ermakovich.ct.web.dto.DancerInPerformance;

/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class PerformanceServiceImplTest extends BaseTest {

    @Mock
    private PerformanceRepository performanceRepository;

    @InjectMocks
    private PerformanceServiceImpl performanceService;

    @Test
    void createsPerformance() {
        Mockito.doReturn(performance)
                .when(performanceRepository)
                .save(Mockito.any(Performance.class));
        Performance actualPerformance = performanceService.create(performance);
        Assertions.assertEquals(
                performance.getTitle(),
                actualPerformance.getTitle(),
                "titles are not equal");
        Assertions.assertEquals(
                performance.getDescription(),
                actualPerformance.getDescription(),
                "descriptions are not equal");
        Mockito.verify(performanceRepository, Mockito.times(1))
                .save(Mockito.any(Performance.class));
    }

    @Test
    void findsById() {
        final String performanceId = "74e5ed85-9343-4441-8862-436bb4d5f07e";
        Mockito.doReturn(Optional.of(BaseTest.performance))
                .when(performanceRepository)
                .findById(Mockito.any(String.class));
        Performance actualPerformance = performanceService.findById(performanceId);
        Assertions.assertEquals(
                BaseTest.performance,
                actualPerformance,
                "performances are not equal");
        Mockito.verify(performanceRepository, Mockito.times(1))
                .findById(Mockito.any(String.class));
    }

    @Test
    void throwsErrorWhenDancerDoesNotExist() {
        final String performanceId = "74e5ed85-9343-4441-8862-436bb4d5f07e";
        Mockito.doReturn(Optional.empty())
                .when(performanceRepository)
                .findById(Mockito.any(String.class));
        Assertions.assertThrows(
                EntityDoesNotExistException.class,
                () -> performanceService.findById(performanceId),
                "exceptions is not thrown"
        );
        Mockito.verify(performanceRepository, Mockito.times(1))
                .findById(Mockito.any(String.class));
    }

    @Test
    void deletesPerformance() {
        final String performanceId = "74e5ed85-9343-4441-8862-436bb4d5f07e";
        Mockito.doNothing()
                .when(performanceRepository)
                .deleteById(Mockito.any(String.class));
        performanceService.delete(performanceId);
        Mockito.verify(performanceRepository, Mockito.times(1))
                .deleteById(Mockito.any(String.class));
    }

    @Test
    void getsRecommendationsBasedOnFriendsLikes() {
        final String visitorId = "74e5ed85-9343-4441-8862-436bb4d5f07e";
        Mockito.doReturn(BaseTest.performanceProjections)
                .when(performanceRepository)
                .getRecommendationsBasedOnFriendsLikes(
                        Mockito.any(String.class)
                );
        List<PerformanceProjection> actualPerformances =
                performanceService
                        .getRecommendationsBasedOnFriendsLikes(visitorId);
        Assertions.assertTrue(
                actualPerformances
                        .stream()
                        .anyMatch(performanceProjection ->
                                        "Cinderella".equals(
                                                performanceProjection.getTitle()
                                        )),
                "actual projection does not contain necessary title");
        Mockito.verify(performanceRepository, Mockito.times(1))
                .getRecommendationsBasedOnFriendsLikes(Mockito.any(String.class));
    }

    @Test
    void addsDancer() {
        final String performanceId = "74e5ed85-9343-4441-8862-436bb4d5f07e";
        final DancerInPerformance dancerInPerformance =
                new DancerInPerformance(
                        dancer.getId(),
                        actsInRelation.getFirstPerformanceDate()
                );
        Mockito.doReturn(BaseTest.performanceWithDancer)
                .when(performanceRepository)
                .addDancer(
                        Mockito.any(DancerInPerformance.class),
                        Mockito.any(String.class)
                );
        Performance actualPerformance = performanceService.addDancer(
                dancerInPerformance,
                performanceId
        );
        Assertions.assertFalse(
                actualPerformance.getDancers().isEmpty(),
                "dancers list is empty");
        Mockito.verify(performanceRepository, Mockito.times(1))
                .addDancer(
                        Mockito.any(DancerInPerformance.class),
                        Mockito.any(String.class)
                );
    }

}
