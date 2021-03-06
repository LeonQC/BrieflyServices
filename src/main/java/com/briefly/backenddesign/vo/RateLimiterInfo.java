package com.briefly.backenddesign.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class RateLimiterInfo {

    private String key;

    @EqualsAndHashCode.Exclude
    private double permitsPerSecond;

}
