package example.lohnsoftware.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityKonfiguration {

    public static final String RolleGeschäftsführer = "Geschäftsführer";
    public static final String RolleMitarbeiter = "Mitarbeiter";

    public static final String AuthorityZeiterfassung = "Zeiterfassung";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((requests) -> requests
                        // i. d. R. Geschäftsführer:innen impliziert für mich, das die Aufgabe auch an andere Mitarbeiter
                        // delegiert werden kann → check nach Authority nicht nach Role
                        .requestMatchers("/api/arbeitsstunden/**").hasAuthority(AuthorityZeiterfassung)
                        .requestMatchers("/error").permitAll()
                        .anyRequest().denyAll()
                ).httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable) // WARNING: Do NOT turn this of if you are using cookies
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails annabelle =
                justADemoProjectUser()
                        .username("Alice")
                        .password("Alice")
                        .roles(RolleGeschäftsführer, RolleMitarbeiter)
                        .authorities(AuthorityZeiterfassung)
                        .build();
        UserDetails ulf =
                justADemoProjectUser()
                        .username("Bob")
                        .password("Bob")
                        .roles(RolleMitarbeiter)
                        .build();
        UserDetails carol =
                justADemoProjectUser()
                        .username("Carol")
                        .password("Carol")
                        .roles(RolleMitarbeiter)
                        .authorities(AuthorityZeiterfassung)
                        .build();
        return new InMemoryUserDetailsManager(annabelle, ulf, carol);
    }

    @SuppressWarnings("deprecation")
    private static User.UserBuilder justADemoProjectUser() {
        return User.withDefaultPasswordEncoder();
    }

}
