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
public class HttpSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("api/arbeitsstunden/**").authenticated()
                        .anyRequest().denyAll()
                ).httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable) // WARNING: Do NOT turn this of if you are using cookies
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails annabelle =
                User.withDefaultPasswordEncoder()
                        .username("Alice")
                        .password("Alice")
                        .roles("ABRECHNER")
                        .build();
        UserDetails ulf =
                User.withDefaultPasswordEncoder()
                        .username("Bob")
                        .password("Bob")
                        .roles("USER")
                        .build();
        UserDetails carol =
                User.withDefaultPasswordEncoder()
                        .username("Carol")
                        .password("Carol")
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(annabelle, ulf, carol);
    }

}
