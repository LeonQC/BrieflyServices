package com.briefly.backenddesign.annotation;


import com.briefly.backenddesign.enums.LimitType;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RateLimit {

    String key() default "";

    String prefix() default "";

    /**
     * 每秒放入令牌桶个数
     * 可以改成float或者double
     */
    double permitsPerSecond();

    /**
     * 每秒放入令牌桶个数
     * 可以改成float或者double
     */
    int period();

    /**
     * 一定时间内最多访问次数
     */
    int permits();

    /**
     * 限流的类型(用户自定义key或者请求ip)
     */
    LimitType limitType() default LimitType.CUSTOMER;
}
