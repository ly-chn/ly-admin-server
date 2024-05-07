package kim.nzxy.ly.common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import kim.nzxy.ly.common.annotation.Lyt;
import kim.nzxy.ly.common.enums.LytEnum;
import kim.nzxy.ly.common.strategy.jackson.LytHandler;
import kim.nzxy.ly.common.strategy.jackson.LytStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LytJsonSerializer extends JsonSerializer<Object> implements ContextualSerializer {
    private Lyt lyt;

    @Override
    public void serialize(Object s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // 首先正常写入
        jsonGenerator.writeObject(s);
        if (lyt == null || s == null) {
            return;
        }
        LytEnum lytEnum = lyt.value();
        Class<?> inType = lytEnum.getInType();
        if (!inType.isInstance(s)) {
            return;
        }
        String fieldName = lyt.field().isBlank() ? lytEnum.getDefaultFieldName() : lyt.field();
        if (fieldName.isBlank()) {
            return;
        }
        //noinspection rawtypes
        LytHandler strategy = LytStrategyFactory.getStrategy(lytEnum);
        //noinspection unchecked
        jsonGenerator.writeObjectField(fieldName, strategy.t(s));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        this.lyt = beanProperty.getAnnotation(Lyt.class);
        return this;
    }
}
