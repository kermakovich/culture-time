package solvd.ermakovich.ct.repository.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import lombok.SneakyThrows;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import solvd.ermakovich.ct.domain.exception.IllegalOperationException;
import solvd.ermakovich.ct.web.dto.DancerInPerformance;

/**
 * @author Ermakovich Kseniya
 */
public class DancerInPerformanceConverter
        implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes () {
        return Set.of(
                new ConvertiblePair(DancerInPerformance.class, Value.class)
        );
    }

    @Override
    public Object convert(final Object source, final TypeDescriptor sourceType, final TypeDescriptor targetType) {
        if (sourceType.getType().equals(DancerInPerformance.class)) {
            return new Converter().toGraphProperty((DancerInPerformance) source);
        }
        else {
            throw new IllegalOperationException("unknown type for converting");
        }
    }
}

class Converter {

    @SneakyThrows
    Value toGraphProperty(final DancerInPerformance value) {
        var mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String result = mapper.writeValueAsString(value);
        return Values.value(result);
    }

}

