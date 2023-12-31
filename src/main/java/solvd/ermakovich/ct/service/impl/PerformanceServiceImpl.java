package solvd.ermakovich.ct.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solvd.ermakovich.ct.domain.exception.EntityDoesNotExistException;
import solvd.ermakovich.ct.domain.node.Performance;
import solvd.ermakovich.ct.repository.PerformanceRepository;
import solvd.ermakovich.ct.repository.projection.PerformanceProjection;
import solvd.ermakovich.ct.service.PerformanceService;
import solvd.ermakovich.ct.web.dto.DancerInPerformance;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;

    @Override
    @Transactional(readOnly = true)
    public Performance findById(final String id) {
        return performanceRepository.findById(id)
                .orElseThrow(() ->
                        new EntityDoesNotExistException(
                                "Performance does not exist"
                        )
                );
    }

    @Override
    @Transactional
    public void delete(final String id) {
        performanceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Performance create(final Performance performance) {
        return performanceRepository.save(performance);
    }

    @Override
    @Transactional
    public Performance addDancer(final DancerInPerformance info,
                                 final String performanceId) {
        return performanceRepository.addDancer(info, performanceId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PerformanceProjection>
        getRecommendationsBasedOnFriendsLikes(final String visitorId) {
        return performanceRepository
                .getRecommendationsBasedOnFriendsLikes(visitorId);
    }

}
