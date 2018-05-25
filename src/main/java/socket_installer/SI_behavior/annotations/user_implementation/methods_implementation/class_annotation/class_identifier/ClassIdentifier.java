package socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassIdentifier {
    String identification();
}
