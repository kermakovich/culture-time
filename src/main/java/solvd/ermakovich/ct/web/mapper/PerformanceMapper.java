package solvd.ermakovich.ct.web.mapper;

import org.mapstruct.Mapper;
import solvd.ermakovich.ct.domain.node.Performance;
import solvd.ermakovich.ct.web.dto.PerformanceDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface PerformanceMapper {

    Performance toEntity(PerformanceDto dto);

    PerformanceDto toDto(Performance performance);

}
