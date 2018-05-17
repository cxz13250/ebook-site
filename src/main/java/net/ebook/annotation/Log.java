package net.ebook.annotation;

import java.lang.annotation.*;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:30 2018/5/17
 * @Modified By:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String content() default "";
}
