package solvd.ermakovich.ct.web.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import solvd.ermakovich.ct.domain.node.Director;
import solvd.ermakovich.ct.domain.relation.ActsIn;

/**
 * @author Ermakovich Kseniya
 */
@Getter
@Setter
public class PerformanceDto {

    private String id;
    private String title;
    private String description;
    private List<ActsIn> dancers;
    private Director director;

}
