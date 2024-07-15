package example.lohnsoftware;

import example.lohnsoftware.cron.CronKonfiguration;
import example.lohnsoftware.http.HttpSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
// todo consistent naming
@Import({HttpSecurityConfiguration.class, CronKonfiguration.class, LohnsoftwareConfiguration.class})
public class LohnsoftwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(LohnsoftwareApplication.class, args);
    }

}
