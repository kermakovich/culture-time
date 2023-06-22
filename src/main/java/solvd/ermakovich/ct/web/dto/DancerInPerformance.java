package solvd.ermakovich.ct.web.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ermakovich Kseniya
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DancerInPerformance {

    private String dancerId;
    private LocalDate firstPerformanceDate;

}
