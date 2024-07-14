package example.lohnsoftware.cron;

import example.lohnsoftware.core.ZeiterfassungImporter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest(properties = "Hintergrundprozess.Zeiterfassung.CronAusdruck=" + HintergrundprozessZeiterfassungTest.JedeSekunde)
class HintergrundprozessZeiterfassungTest {

    public static final String JedeSekunde = "*/1 * * * * *";

    @TestConfiguration
    public static class Config {

        @Bean
        @Primary
        public ZeiterfassungImporter arbeitsstundenImporteZähler() {
            return new ArbeitsstundenImporteZähler();
        }
    }

    @Test
    void ausführungNachKonfiguriertemCronAusdruck(@Autowired ArbeitsstundenImporteZähler importer) {
        await()
                .atMost(Duration.ofSeconds(2))
                .untilAsserted(() -> assertThat(importer.anzahlImporte).isGreaterThanOrEqualTo(2));
    }

}
