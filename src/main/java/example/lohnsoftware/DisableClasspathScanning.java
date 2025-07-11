package example.lohnsoftware;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <a href="https://docs.spring.io/spring-framework/reference/core/beans/classpath-scanning.html#beans-scanning-filters">classpath-scanning.html</a>
 * Intend is to enforce manual bean registration and not using
 *
 * <ul>
 *   <li>{@link org.springframework.stereotype.Indexed @Indexed} </li>
 *   <li>{@link org.springframework.stereotype.Component @Component} </li>
 *   <li>{@link org.springframework.stereotype.Controller @Controller} </li>
 *   <li>{@link org.springframework.stereotype.Repository @Repository}</li>
 *   <li>{@link org.springframework.stereotype.Service @Service} </li>
 * </ul>
 */
@Configuration
@ComponentScan(useDefaultFilters = false)
public class DisableClasspathScanning {
}
