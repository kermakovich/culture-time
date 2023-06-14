package solvd.ermakovich.ct.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    DancerDto create(@RequestBody DancerDto dancerDto) {
        Dancer dancer = mapper.toEntity(dancerDto);
        dancer = dancerService.create(dancer);
        return mapper.toDto(dancer);
    }

}
