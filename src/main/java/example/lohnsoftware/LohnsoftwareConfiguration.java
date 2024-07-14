package example.lohnsoftware;

import example.lohnsoftware.core.*;
import example.lohnsoftware.infrastructure.FiktiveZeiterfassung;
import example.lohnsoftware.infrastructure.JsonGestützteBelegschaft;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class LohnsoftwareConfiguration {

    @Bean
    public Zeiterfassung zeiterfassung() {
        final var pfadZurZeiterfassung = Path.of("daten", "zeiterfassung.json");
        return new FiktiveZeiterfassung(pfadZurZeiterfassung);
    }

    @Bean
    public AktualisiereMonatsArbeitsstunden lohnsoftware() {
        return new StandardAktualisiereMonatsArbeitsstunden();
    }

    @Bean
    public Belegschaft belegschaft() {
        final var pfadZurBelegschaft = Path.of("daten", "belegschaft.json");
        return new JsonGestützteBelegschaft(pfadZurBelegschaft);
    }

    @Bean
    public Uhr uhr() {
        return new SystemUhr();
    }

}

