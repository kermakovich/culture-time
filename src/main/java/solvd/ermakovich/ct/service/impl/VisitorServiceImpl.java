package solvd.ermakovich.ct.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solvd.ermakovich.ct.domain.node.Visitor;
import solvd.ermakovich.ct.repository.VisitorRepository;
import solvd.ermakovich.ct.service.VisitorService;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    @Override
    @Transactional
    public Visitor create(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    @Transactional
    public Visitor makeFriend(String from, String to) {
        return visitorRepository.makeFriend(from, to);
    }

}
