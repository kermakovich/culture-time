package solvd.ermakovich.ct.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import solvd.ermakovich.ct.domain.node.Visitor;
import solvd.ermakovich.ct.repository.projection.PerformanceProjection;
import solvd.ermakovich.ct.service.PerformanceService;
import solvd.ermakovich.ct.service.VisitorService;
import solvd.ermakovich.ct.web.dto.VisitorDto;
import solvd.ermakovich.ct.web.mapper.VisitorMapper;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("api/v1/visitors")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorMapper mapper;
    private final PerformanceService performanceService;
    private final VisitorService visitorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitorDto create(@RequestBody VisitorDto visitorDto) {
        Visitor visitor = mapper.toEntity(visitorDto);
        visitor = visitorService.create(visitor);
        return mapper.toDto(visitor);
    }

    @PutMapping("/{id}")
    public VisitorDto makeFriend(@RequestBody String friendId,
                          @PathVariable String id) {
        Visitor visitor = visitorService.makeFriend(id, friendId);
        return mapper.toDto(visitor);
    }

    @GetMapping("/{id}/recommendations")
    public List<PerformanceProjection> getRecommendations(@PathVariable String id) {
        return performanceService
                .getRecommendationsBasedOnFriendsLikes(id);
    }

}
