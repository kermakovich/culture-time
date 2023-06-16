package solvd.ermakovich.ct.web.dto;

import java.time.LocalDate;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class DancerInPerformance {

    private String dancerId;
    private LocalDate firstPerformanceDate;

}
