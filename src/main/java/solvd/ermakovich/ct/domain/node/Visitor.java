package solvd.ermakovich.ct.domain.node;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

/**
 * @author Ermakovich Kseniya
 */
@Node
@Getter
@Setter
public class Visitor {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
    private String surname;

    @Relationship(type = "FRIEND")
    private List<Visitor> friends;

    @Relationship(type = "VISITED")
    private List<Performance> performances;

}
