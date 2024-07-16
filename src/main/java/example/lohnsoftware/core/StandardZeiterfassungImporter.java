package example.lohnsoftware.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardZeiterfassungImporter implements ZeiterfassungImporter {
    private Logger logger = LoggerFactory.getLogger("ZeiterfassungImporter");

    private final Zeiterfassung zeiterfassung;
    private final AktualisiereMonatsArbeitsstunden aktualisiereMonatsArbeitsstunden;
    private final Belegschaft belegschaft;
    private final Uhr uhr;

    public StandardZeiterfassungImporter(Zeiterfassung zeiterfassung, AktualisiereMonatsArbeitsstunden aktualisiereMonatsArbeitsstunden, Belegschaft belegschaft, Uhr uhr) {
        this.zeiterfassung = zeiterfassung;
        this.aktualisiereMonatsArbeitsstunden = aktualisiereMonatsArbeitsstunden;
        this.belegschaft = belegschaft;
        this.uhr = uhr;
    }

    @Override
    public void importiereArbeitsstunden() {
        logger.info("{\"prozess\": \"ZeiterfassungImporter\", \"status\": \"start\"}");
        final var month = LocalMonth.from(this.uhr.heute());
        this.belegschaft.alleMitarbeiter().forEach(mitarbeiter -> importiereArbeitsstundenFür(mitarbeiter, month));
        logger.info("{\"prozess\": \"ZeiterfassungImporter\", \"status\": \"abgeschlossen\"}");
    }

    private void importiereArbeitsstundenFür(Mitarbeiter mitarbeiter, LocalMonth month) {
        this.zeiterfassung.arbeitsstundenFür(mitarbeiter, month)
                .map(arbeitsstunden -> new MonatsArbeitsstunden(month, mitarbeiter, arbeitsstunden))
                .ifPresent(this.aktualisiereMonatsArbeitsstunden::aktualisiere);
    }

}
