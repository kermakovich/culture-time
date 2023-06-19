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
    DancerDto create(@RequestBody DancerDto dancerDto) {
        Dancer dancer = mapper.toEntity(dancerDto);
        dancer = dancerService.create(dancer);
        return mapper.toDto(dancer);
    }

    @GetMapping("/{dancerId}")
    DancerDto getById(@PathVariable String dancerId) {
        Dancer dancer = dancerService.findById(dancerId);
        return mapper.toDto(dancer);
    }

    @GetMapping("/{dancerId}/performances/count")
    Long getPerformancesCount(@PathVariable String dancerId) {
        return dancerService.getPerformancesCount(dancerId);
    }

    @DeleteMapping("/{dancerId}")
    void deleteById(@PathVariable String dancerId) {
        dancerService.delete(dancerId);
    }

}
