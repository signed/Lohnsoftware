package example.lohnsoftware;

import example.lohnsoftware.cron.CronKonfiguration;
import example.lohnsoftware.http.SpringSecurityKonfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DisableClasspathScanning.class, SpringSecurityKonfiguration.class, CronKonfiguration.class, LohnsoftwareKonfiguration.class})
public class LohnsoftwareApplication {

  public static void main(String[] args) {
    var application = new SpringApplication(LohnsoftwareApplication.class);
    application.setBannerMode(Banner.Mode.OFF);
    application.run(args);
  }

}
