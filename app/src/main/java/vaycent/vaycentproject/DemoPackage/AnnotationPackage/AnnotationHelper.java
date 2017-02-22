package vaycent.vaycentproject.DemoPackage.AnnotationPackage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by vaycent on 2017/2/22.
 */

public class AnnotationHelper {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface BindPort {
        String value() default "8080";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface BindAddress {
        String value() default "127.0.0.0";
    }
}
