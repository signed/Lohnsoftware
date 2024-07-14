package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.util.Set;

@Configuration
public class LohnsoftwareConfiguration {

    @Bean
    public Zeiterfassung zeiterfassung() {
        final var pfadZurZeiterfassung = Path.of("daten", "zeiterfassung.json");
        return new FiktiveZeiterfassung(pfadZurZeiterfassung);
    }

    @Bean
    public Lohnsoftware lohnsoftware() {
        return new Lohnsoftware() {
            @Override
            public void schreibeArbeitsstundenFÜr(Mitarbeiter mitarbeiter, LocalMonth month, Arbeitsstunden arbeitsstunden) {
                //todo implement
            }
        };
    }


    @Bean
    public Belegschaft belegschaft() {
        return new JsonGestützteBelegschaft();
    }

    @Bean
    public Uhr uhr() {
        return new SystemUhr();
    }

    private static class JsonGestützteBelegschaft implements Belegschaft {
        @Override
        public Set<Mitarbeiter> alleMitarbeiter() {
            return Set.of();
        }
    }
}

