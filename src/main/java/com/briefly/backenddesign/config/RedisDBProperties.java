package com.briefly.backenddesign.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "briefly.redis.db")
@Data
public class RedisDBProperties {
    private String host;
    private Integer port;
}
