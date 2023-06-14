package solvd.ermakovich.ct.repository.config;

import java.util.Collections;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.data.neo4j.core.convert.Neo4jConversions;

/**
 * @author Ermakovich Kseniya
 */
@Configuration
public class Neo4jConfig {

    @Bean
    public Neo4jConversions neo4jConversions() {
        Set<GenericConverter> customConverters =
                Collections.singleton(new DancerInPerformanceConverter());
        return new Neo4jConversions(customConverters);
    }

}
