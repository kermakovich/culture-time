package solvd.ermakovich.ct.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import solvd.ermakovich.ct.domain.node.Visitor;

/**
 * @author Ermakovich Kseniya
 */
public interface VisitorRepository extends Neo4jRepository<Visitor, String> {

    @Query("MATCH(from:Visitor) WHERE  from.id= :#{#from} "
            + "match(to:Visitor) WHERE to.id= :#{#to} "
            + "create (from)-[rel:FRIEND]"
            + "->(to) return from")
    Visitor makeFriend(String from, String to);

}
