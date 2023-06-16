package solvd.ermakovich.ct.domain.node;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import solvd.ermakovich.ct.domain.relation.ActsIn;

/**
 * @author Ermakovich Kseniya
 */
@Node
@Getter
@Setter
public class Performance {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String title;
    private String description;

    @Relationship(type = "ACTS_IN", direction = Relationship.Direction.INCOMING)
    private List<ActsIn> dancers;

    @Relationship(type = "STAGES", direction = Relationship.Direction.INCOMING)
    private Director director;

}
