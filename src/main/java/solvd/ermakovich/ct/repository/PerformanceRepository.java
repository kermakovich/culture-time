package solvd.ermakovich.ct.repository;

import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import solvd.ermakovich.ct.domain.node.Performance;
import solvd.ermakovich.ct.repository.projection.PerformanceProjection;
import solvd.ermakovich.ct.web.dto.DancerInPerformance;

/**
 * @author Ermakovich Kseniya
 */
public interface PerformanceRepository
        extends Neo4jRepository<Performance, String> {

    @Query("MATCH(dancer:Dancer) WHERE  dancer.id= :#{#info.dancerId} "
            + "match(perf:Performance) WHERE perf.id= :#{#performanceId} "
            + "merge (dancer)-[rela:ACTS_IN {firstPerformanceDate:"
            + " :#{#info.firstPerformanceDate}}]"
            + "->(perf) return perf, collect(rela), collect(dancer)")
    Performance addDancer(
            DancerInPerformance info,
            String performanceId
    );

    @Query("MATCH (visitor:Visitor) where visitor.id= :#{#id} "
            + "match (visitor) -[rel:FRIEND] ->(friends:Visitor) "
            + "- [re:LIKED] -> (perf:Performance) return perf")
    List<PerformanceProjection>
        getRecommendationsBasedOnFriendsLikes(String id);

}
