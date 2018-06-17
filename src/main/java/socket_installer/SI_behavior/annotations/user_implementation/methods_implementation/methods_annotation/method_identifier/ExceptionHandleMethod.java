package socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionHandleMethod {
    String identification();
}
