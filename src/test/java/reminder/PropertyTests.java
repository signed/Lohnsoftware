package reminder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;

import static reminder.SemanticVersionAssert.assertThat;

public class PropertyTests {

    @Test
    void reminderToLookIntoNewSpringBootProperty() {
        var springBootVersion = springBootVersion();

        assertThat(springBootVersion)
                .withFailMessage("use new spring boot configuration property https://spring.io/blog/2025/11/18/opentelemetry-with-spring-boot#beware-the-context")
                .isBefore("4.1.0");
    }

    private SemanticVersion springBootVersion() {
        var versionString = SpringBootVersion.getVersion();
        return SemanticVersion.parseFrom(versionString);
    }

}
