package com.kakaotalk.gift.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    private static final String REDISSON_HOST_PREFIX = "redis://";
    private static final String TOPIC_NAME = "GIFT";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(REDISSON_HOST_PREFIX + host + ":" + port);
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new RedissonConnectionFactory(redissonClient());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());        // key 깨짐 방지
        redisTemplate.setValueSerializer(new StringRedisSerializer());      // value 깨짐 방지

        return redisTemplate;
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic(TOPIC_NAME);
    }

//    /**
//     * redis listener adapter
//     */
//    @Bean
//    MessageListenerAdapter messageListenerAdapter() {
//        return new MessageListenerAdapter(new ReceivedGiftService());
//    }
//
//    /**
//     * redis message listener container
//     */
//    @Bean
//    RedisMessageListenerContainer redisContainer() {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(redisConnectionFactory());
//        container.addMessageListener(messageListenerAdapter(), topic());
//
//        return container;
//    }
}
