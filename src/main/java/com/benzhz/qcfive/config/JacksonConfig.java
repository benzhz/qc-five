package com.benzhz.qcfive.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.config
 * @Project：qc-five
 * @name：JacksonConfig
 * @Date：2025/2/16 22:15
 * @Filename：JacksonConfig
 */
@Slf4j
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ObjectId.class, new JsonSerializer<ObjectId>() {
            @Override
            public void serialize(ObjectId value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(value.toHexString());
            }
        });
        module.addSerializer(Map.class, new JsonSerializer<Map>() {
            /**
             * 转换
             *
             * @param value       value
             * @param gen         gen
             * @param serializers serializers
             */
            @Override
            public void serialize(Map value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeStartObject();
                if (MapUtils.isEmpty(value)) {
                    return;
                }
                value.forEach((key, val) -> {
                    try {
                        if (val instanceof ObjectId) {
                            val = ((ObjectId) val).toHexString();
                        }
                        gen.writeObjectField( key.toString(), val);
                    } catch (Exception e) {
                        log.error("map转换对象异常", e);
                    }
                });
                gen.writeEndObject();
            }
        });
        module.addDeserializer(ObjectId.class, new JsonDeserializer<ObjectId>() {
            @Override
            public ObjectId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                return new ObjectId(p.getText());
            }
        });
        mapper.registerModule(module);
        return mapper;
    }
}
