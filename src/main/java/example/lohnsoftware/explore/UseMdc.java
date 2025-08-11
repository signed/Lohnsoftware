package example.lohnsoftware.explore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class UseMdc implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(UseMdc.class);

    @Override
    public void run(String... args) {

      try {
        MDC.put("mitarbeiterId", "1");
        LOGGER.info("""
          {
            "one": "Hello structured logging!"
          }""");
        LOGGER.info("""
          {
            "two": "Also contains the mitarbeiterId"
          }""");
      } finally {
        MDC.remove("mitarbeiterId");
      }
    }
}