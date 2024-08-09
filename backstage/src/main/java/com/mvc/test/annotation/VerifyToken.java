package com.mvc.test.annotation;

import java.lang.annotation.*;

// 注解：用于添加对该接口的用户验证

@Target({ElementType.METHOD,ElementType.TYPE}) // 可以应用于方法
@Retention(RetentionPolicy.RUNTIME) // 在运行时可用
@Documented
// 验证令牌
public @interface VerifyToken {
    boolean required() default true;
}