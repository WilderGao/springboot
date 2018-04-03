package com.qg.springboot.limit;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
    int count() default 5;
    /**
     * 时间段，单位为毫秒
     */
    long time() default 30000;
}
