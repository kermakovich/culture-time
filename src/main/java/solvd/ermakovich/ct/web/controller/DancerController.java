package solvd.ermakovich.ct.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import solvd.ermakovich.ct.domain.node.Dancer;
import solvd.ermakovich.ct.service.DancerService;
import solvd.ermakovich.ct.web.dto.DancerDto;
import solvd.ermakovich.ct.web.mapper.DancerMapper;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("api/v1/dancers")
@RequiredArgsConstructor
public class DancerController {

    private final DancerMapper mapper;
    private final DancerService dancerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DancerDto create(@RequestBody final DancerDto dancerDto) {
        Dancer dancer = mapper.toEntity(dancerDto);
        dancer = dancerService.create(dancer);
        return mapper.toDto(dancer);
    }

    @GetMapping("/{dancerId}")
    public DancerDto getById(@PathVariable final String dancerId) {
        Dancer dancer = dancerService.findById(dancerId);
        return mapper.toDto(dancer);
    }

    @GetMapping("/{dancerId}/performances/count")
    public Long getPerformancesCount(@PathVariable final String dancerId) {
        return dancerService.getPerformancesCount(dancerId);
    }

    @DeleteMapping("/{dancerId}")
    public void deleteById(@PathVariable final String dancerId) {
        dancerService.delete(dancerId);
    }

}
