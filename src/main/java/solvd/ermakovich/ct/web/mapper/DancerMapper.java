package solvd.ermakovich.ct.web.mapper;

import org.mapstruct.Mapper;
import solvd.ermakovich.ct.domain.node.Dancer;
import solvd.ermakovich.ct.web.dto.DancerDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface DancerMapper {

    Dancer toEntity(DancerDto dto);

    DancerDto toDto(Dancer dancer);

}
