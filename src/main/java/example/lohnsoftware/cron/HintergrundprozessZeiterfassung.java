package example.lohnsoftware.cron;

import example.lohnsoftware.core.ZeiterfassungImporter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HintergrundprozessZeiterfassung {

    final ZeiterfassungImporter zeiterfassungImporter;

    public HintergrundprozessZeiterfassung(ZeiterfassungImporter zeiterfassungImporter) {
        this.zeiterfassungImporter = zeiterfassungImporter;
    }

    @Scheduled(cron = "${Hintergrundprozess.Zeiterfassung.CronAusdruck}")
    public void importiereArbeitsstundenFürAlleMitarbeiter() {
        this.zeiterfassungImporter.importiereArbeitsstunden();
    }
}
