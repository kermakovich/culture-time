package solvd.ermakovich.ct.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import solvd.ermakovich.ct.domain.node.Dancer;

/**
 * @author Ermakovich Kseniya
 */
public interface DancerRepository extends Neo4jRepository<Dancer, String> {

    @Query("MATCH (d:Dancer) WHERE d.id= :#{#dancerId} " +
            "MATCH (d) -[:ACTS_IN] -> (p:Performance) RETURN count(p)")
    Long getPerformancesCount(String dancerId);

}
