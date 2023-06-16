package solvd.ermakovich.ct.service;

import java.util.List;
import solvd.ermakovich.ct.domain.node.Performance;
import solvd.ermakovich.ct.repository.projection.PerformanceProjection;
import solvd.ermakovich.ct.web.dto.DancerInPerformance;

/**
 * @author Ermakovich Kseniya
 */
public interface PerformanceService {

    Performance create(Performance performance);

    Performance findById(String id);

    List<PerformanceProjection> getRecommendationsBasedOnFriendsLikes(String visitorId);

    Performance addDancer(DancerInPerformance info, String performanceId);

    void delete(String id);

}
