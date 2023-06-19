package solvd.ermakovich.ct.domain.node;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

/**
 * @author Ermakovich Kseniya
 */
@Node
@Getter
@Setter
public class Director {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
    private String surname;

}
