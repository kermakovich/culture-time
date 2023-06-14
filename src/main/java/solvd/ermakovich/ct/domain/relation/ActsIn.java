package solvd.ermakovich.ct.domain.relation;


import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import solvd.ermakovich.ct.domain.node.Dancer;

/**
 * @author Ermakovich Kseniya
 */
@RelationshipProperties
@Data
public class ActsIn {

    @RelationshipId
    private Long id;
    private LocalDate firstPerformanceDate;

    @TargetNode
    private Dancer dancer;

}
