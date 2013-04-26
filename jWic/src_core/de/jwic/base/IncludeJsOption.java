package de.jwic.base;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

/**
 * 
 * @author dotto
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({METHOD})
public @interface IncludeJsOption {

}
