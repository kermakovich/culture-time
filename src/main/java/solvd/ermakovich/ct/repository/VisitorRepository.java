package solvd.ermakovich.ct.repository;

import java.util.List;
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
            + "->(to) return from, collect(rel), collect(to)")
    Visitor makeFriend(String from, String to);

    @Query("MATCH (source:Visitor) WHERE source.id=:#{#from} "
            + "MATCH (target:Visitor) WHERE target.id=:#{#to} "
            + "MATCH p = shortestPath((source)-[:FRIEND*]-(target)) "
            + "return p")
    List<Visitor> getFriendsShortestPath(String from, String to);

}
