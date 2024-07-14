package example.lohnsoftware.cron;

import example.lohnsoftware.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class CronKonfiguration {

    @Bean
    public ZeiterfassungImporter zeiterfassungImporter(
            @Autowired Zeiterfassung zeiterfassung,
            @Autowired AktualisiereMonatsArbeitsstunden aktualisiereMonatsArbeitsstunden,
            @Autowired Belegschaft belegschaft,
            @Autowired Uhr uhr) {
        return new StandardZeiterfassungImporter(zeiterfassung, aktualisiereMonatsArbeitsstunden, belegschaft, uhr);
    }
}
