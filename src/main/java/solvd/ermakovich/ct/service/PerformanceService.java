package solvd.ermakovich.ct.service;

import solvd.ermakovich.ct.domain.node.Performance;
import solvd.ermakovich.ct.web.dto.DancerInPerformance;

/**
 * @author Ermakovich Kseniya
 */
public interface PerformanceService {

    Performance findById(String id);

    Performance create(Performance performance);

    Performance addDancer(DancerInPerformance info, String performanceId);

}
