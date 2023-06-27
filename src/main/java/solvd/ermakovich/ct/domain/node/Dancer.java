package solvd.ermakovich.ct.domain.node;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

/**
 * @author Ermakovich Kseniya
 */
@Node
@Data
public class Dancer {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
    private String surname;
    private String description;
    private Float experience;

}
