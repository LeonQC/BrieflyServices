package com.briefly.backenddesign.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableConfigurationProperties(RedisDBProperties.class)
@EnableRedisRepositories
public class RedisDBConfig {

  @Bean
  public LettuceConnectionFactory redisConnectionFactory(RedisDBProperties redisProperties) {
    return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
  }

  @Bean
  public RedisTemplate<String, Long> sequenceRedisTemplate(
      LettuceConnectionFactory redisConnectionFactory) {

    RedisTemplate<String, Long> redisTemplate = new RedisTemplate<String, Long>();

    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericToStringSerializer<Long>(Long.class));
    redisTemplate.setExposeConnection(true);
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.afterPropertiesSet();

    return redisTemplate;
  }
}
