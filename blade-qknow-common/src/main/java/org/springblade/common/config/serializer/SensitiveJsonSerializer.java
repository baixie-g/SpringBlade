package org.springblade.common.config.serializer;

import java.io.IOException;
import java.util.Objects;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

/**
 * 数据脱敏序列化过滤
 * 简化版本，移除复杂的依赖
 * @author SpringBlade
 */
public class SensitiveJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {
    
    private String desensitizedType;
    
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (desensitization()) {
            gen.writeString(desensitize(value));
        } else {
            gen.writeString(value);
        }
    }
    
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
            throws JsonMappingException {
        // 简化版本，直接返回当前序列化器
        return this;
    }
    
    /**
     * 是否需要脱敏处理
     * 简化版本，默认返回false
     */
    private boolean desensitization() {
        // 简化版本，可以根据需要扩展
        return false;
    }
    
    /**
     * 简单的脱敏处理
     */
    private String desensitize(String value) {
        if (value == null || value.length() <= 2) {
            return value;
        }
        // 简单的脱敏：保留首尾字符，中间用*替换
        return value.charAt(0) + "*".repeat(value.length() - 2) + value.charAt(value.length() - 1);
    }
}
