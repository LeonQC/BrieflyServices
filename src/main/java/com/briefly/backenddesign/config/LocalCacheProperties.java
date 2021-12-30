package com.briefly.backenddesign.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tinyurl.local.cache")
@Data
public class LocalCacheProperties {
    private Integer keepAliveTime;

    public long keepAliveTime() {
        return 0;
    }
}
