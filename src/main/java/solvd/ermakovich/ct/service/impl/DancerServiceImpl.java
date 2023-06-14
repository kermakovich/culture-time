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
    public Dancer create(Dancer dancer) {
        return dancerRepository.save(dancer);
    }

    @Override
    public Dancer findById(String dancerId) {
        return dancerRepository.findById(dancerId)
                .orElseThrow(() -> new EntityDoesNotExistException("dancer does not exist"));
    }

}
