package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Retention 元注解，描述注解的注解
//使用状态、策略  SOURCE源码  CLASS编译器  RUNTIME运行时

@Retention(RetentionPolicy.RUNTIME)

//注解接口
public @interface TestAnnotation {
    String[] value() default "unknown";
}
