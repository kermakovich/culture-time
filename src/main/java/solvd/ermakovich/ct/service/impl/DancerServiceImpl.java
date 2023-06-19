package solvd.ermakovich.ct.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solvd.ermakovich.ct.domain.exception.EntityDoesNotExistException;
import solvd.ermakovich.ct.domain.node.Dancer;
import solvd.ermakovich.ct.repository.DancerRepository;
import solvd.ermakovich.ct.service.DancerService;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class DancerServiceImpl implements DancerService {

    private final DancerRepository dancerRepository;

    @Override
    @Transactional
    public Dancer create(final Dancer dancer) {
        return dancerRepository.save(dancer);
    }

    @Override
    @Transactional(readOnly = true)
    public Dancer findById(final String dancerId) {
        return dancerRepository.findById(dancerId)
                .orElseThrow(() ->
                        new EntityDoesNotExistException(
                                "dancer does not exist"
                        )
                );
    }

    @Override
    @Transactional(readOnly = true)
    public Long getPerformancesCount(final String dancerId) {
        return dancerRepository.getPerformancesCount(dancerId);
    }

    @Override
    @Transactional
    public void delete(final String dancerId) {
        dancerRepository.deleteById(dancerId);
    }

}
