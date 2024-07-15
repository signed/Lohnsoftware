package example.lohnsoftware.http;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static example.lohnsoftware.http.SpringSecurityKonfiguration.AuthorityZeiterfassung;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@WithMockUser(authorities = {AuthorityZeiterfassung})
public @interface BerechtigungArbeitsstundenErfassen {
}
