package example.lohnsoftware.explore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.interceptor.LoggingCacheErrorHandler;
import org.springframework.stereotype.Component;

@Component
class UseMdc implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(UseMdc.class);

    @Override
    public void run(String... args) {

        try (MDC.MDCCloseable a = MDC.putCloseable("auto", "close")) {
            MDC.pushByKey("stacked", "A");
            LOGGER.atInfo().log(() -> "first");
            MDC.pushByKey("stacked", "B"); // does not show up in logs yet
            try (MDC.MDCCloseable b = MDC.putCloseable("2nd", "value")) {
                LOGGER.atInfo().log(() -> "second");
                MDC.popByKey("stacked");
            }
            LOGGER.atInfo().log(() -> "third");
            MDC.getMDCAdapter().clearDequeByKey("stacked");
        }

        LOGGER.atInfo().log(()-> "and done");

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