package solvd.ermakovich.ct.web.mapper;

import org.mapstruct.Mapper;
import solvd.ermakovich.ct.domain.node.Visitor;
import solvd.ermakovich.ct.web.dto.VisitorDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface VisitorMapper {

    Visitor toEntity(VisitorDto dto);

    VisitorDto toDto(Visitor performance);

}
