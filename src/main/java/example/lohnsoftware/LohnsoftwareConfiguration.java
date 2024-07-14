package example.lohnsoftware;

import example.lohnsoftware.core.*;
import example.lohnsoftware.infrastructure.FiktiveZeiterfassung;
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
    public Belegschaft belegschart() {
        //todo implement
        return new Belegschaft() {
            @Override
            public Set<Mitarbeiter> alleMitarbeiter() {
                return Set.of();
            }
        };
    }

    @Bean
    public Uhr uhr() {
        return new SystemUhr();
    }

}

