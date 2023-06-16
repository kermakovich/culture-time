package solvd.ermakovich.ct.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import solvd.ermakovich.ct.domain.node.Dancer;

/**
 * @author Ermakovich Kseniya
 */
public interface DancerRepository extends Neo4jRepository<Dancer, String> {

}
