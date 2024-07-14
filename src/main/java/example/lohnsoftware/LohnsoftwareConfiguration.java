package example.lohnsoftware;

import example.lohnsoftware.core.*;
import example.lohnsoftware.infrastructure.FiktiveZeiterfassung;
import example.lohnsoftware.infrastructure.JsonGestützteBelegschaft;
import example.lohnsoftware.infrastructure.JsonGestützterArbeitszeitkonto;
import example.lohnsoftware.infrastructure.SystemUhr;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AktualisiereMonatsArbeitsstunden lohnsoftware(@Autowired Arbeitszeitkonto arbeitszeitkonto) {
        return new StandardAktualisiereMonatsArbeitsstunden(arbeitszeitkonto);
    }

    @Bean
    public Belegschaft belegschaft() {
        final var pfadZurBelegschaft = Path.of("daten", "belegschaft.json");
        return new JsonGestützteBelegschaft(pfadZurBelegschaft);
    }

    @Bean
    public Arbeitszeitkonto monatsArbeitsstundenSchreiber(){
        return new JsonGestützterArbeitszeitkonto();
    }

    @Bean
    public Uhr uhr() {
        return new SystemUhr();
    }

}

