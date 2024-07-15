package example.lohnsoftware;

import example.lohnsoftware.cron.CronKonfiguration;
import example.lohnsoftware.http.SpringSecurityKonfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SpringSecurityKonfiguration.class, CronKonfiguration.class, LohnsoftwareKonfiguration.class})
public class LohnsoftwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(LohnsoftwareApplication.class, args);
    }

}
