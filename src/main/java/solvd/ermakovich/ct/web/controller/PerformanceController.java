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
import solvd.ermakovich.ct.domain.node.Performance;
import solvd.ermakovich.ct.service.PerformanceService;
import solvd.ermakovich.ct.web.dto.DancerInPerformance;
import solvd.ermakovich.ct.web.dto.PerformanceDto;
import solvd.ermakovich.ct.web.mapper.PerformanceMapper;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("api/v1/performances")
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceMapper mapper;
    private final PerformanceService dancerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PerformanceDto create(
            @RequestBody final PerformanceDto performanceDto
    ) {
        Performance performance = mapper.toEntity(performanceDto);
        performance = dancerService.create(performance);
        return mapper.toDto(performance);
    }

    @GetMapping("/{performanceId}")
    public PerformanceDto getById(@PathVariable final String performanceId) {
        var performance = dancerService.findById(performanceId);
        return mapper.toDto(performance);
    }

    @PostMapping("/{performanceId}/dancers")
    public PerformanceDto addDancer(
            @RequestBody final DancerInPerformance dancerInPerformance,
            @PathVariable final String performanceId
    ) {
        var performance = dancerService
                .addDancer(dancerInPerformance, performanceId);
        return mapper.toDto(performance);
    }

    @DeleteMapping("/{performanceId}")
    public void delete(@PathVariable final String performanceId) {
        dancerService.delete(performanceId);
    }

}
