package solvd.ermakovich.ct.service;

import solvd.ermakovich.ct.domain.node.Dancer;

/**
 * @author Ermakovich Kseniya
 */
public interface DancerService {

    Dancer create(Dancer dancer);

    Dancer findById(String dancerId);

    Long getPerformancesCount(String dancerId);

    void delete(String dancerId);

}
