package solvd.ermakovich.ct.domain.node;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

/**
 * @author Ermakovich Kseniya
 */
@Node
@Data
@NoArgsConstructor
public class Dancer {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
    private String surname;
    private String description;
    private Float experience;

    public Dancer(Dancer dancer) {
        this.id = dancer.getId();
        this.name = dancer.getName();
        this.surname = dancer.getSurname();
        this.description = dancer.getDescription();
        this.experience = dancer.getExperience();
    }

}
