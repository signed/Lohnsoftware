package example.lohnsoftware.explore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
// https://www.slf4j.org/manual.html#fluent
class UseFluentApi implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(UseFluentApi.class);

    @Override
    public void run(String... args) {
      LOGGER.atInfo().setMessage("the fluent way").addKeyValue("three", "fluent").log();
      LOGGER.atInfo().setMessage("the fluent way 2").log();
    }
}