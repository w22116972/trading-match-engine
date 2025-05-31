package io.github.anderwang.trading.matching.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;
import java.util.Map;

@Configuration
public class RedisConfig {
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public StringRedisTemplate redisTemplate(LettuceConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    public DefaultRedisScript<List<Map<String, Object>>> matchingScript() {
        DefaultRedisScript<List<Map<String, Object>>> script = new DefaultRedisScript<>();
        script.setScriptSource(
            new ResourceScriptSource(
                new ClassPathResource("lua/matching.lua")
            )
        );
        // 由於 List<Map<String,Object>> 在編譯時會被擦除成 List，
        // 這裡必須把 List.class 強制轉型成 Class<List<Map<String,Object>>>：
        @SuppressWarnings("unchecked")
        Class<List<Map<String, Object>>> resultType =
                (Class<List<Map<String, Object>>>) (Class<?>) List.class;
        script.setResultType(resultType);
        return script;
    }
}
