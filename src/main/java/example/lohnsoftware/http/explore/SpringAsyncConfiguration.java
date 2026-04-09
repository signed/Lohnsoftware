package example.lohnsoftware.http.explore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class SpringAsyncConfiguration {

    @Bean
    public AsyncLogik asyncLogik() {
        return new DefaultAsyncLogik();
    }
}
