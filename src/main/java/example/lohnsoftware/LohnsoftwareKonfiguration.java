package example.lohnsoftware;

import example.lohnsoftware.core.*;
import example.lohnsoftware.infrastructure.FiktiveZeiterfassung;
import example.lohnsoftware.infrastructure.JsonArbeitszeitkonto;
import example.lohnsoftware.infrastructure.JsonBelegschaft;
import example.lohnsoftware.infrastructure.SystemUhr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class LohnsoftwareKonfiguration {

    @Bean
    public Zeiterfassung zeiterfassung() {
        final var pfadZurZeiterfassung = Path.of("daten", "zeiterfassung.json");
        return new FiktiveZeiterfassung(pfadZurZeiterfassung);
    }

    @Bean
    public AktualisiereMonatsArbeitsstunden lohnsoftware(@Autowired Belegschaft belegschaft, @Autowired Arbeitszeitkonto arbeitszeitkonto) {
        return new StandardAktualisiereMonatsArbeitsstunden(belegschaft, arbeitszeitkonto);
    }

    @Bean
    public Belegschaft belegschaft() {
        final var pfadZurBelegschaft = Path.of("daten", "belegschaft.json");
        return new JsonBelegschaft(pfadZurBelegschaft);
    }

    @Bean
    public Arbeitszeitkonto monatsArbeitsstundenSchreiber() {
        final var pfadZumArbeitszeitkonto = Path.of("daten", "arbeitszeitkonto");
        return new JsonArbeitszeitkonto(pfadZumArbeitszeitkonto);
    }

    @Bean
    public Uhr uhr() {
        return new SystemUhr();
    }

}

