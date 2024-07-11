package example.lohnsoftware.core;

import java.time.Year;

public class ZeiterfassungImporter {

    private final Zeiterfassung zeiterfassung;
    private final Lohnsoftware lohnsoftware;
    private final Belegschaft belegschaft;
    private final Uhr uhr;

    public ZeiterfassungImporter(Zeiterfassung zeiterfassung, Lohnsoftware lohnsoftware, Belegschaft belegschaft, Uhr uhr) {
        this.zeiterfassung = zeiterfassung;
        this.lohnsoftware = lohnsoftware;
        this.belegschaft = belegschaft;
        this.uhr = uhr;
    }

    public void importiereArbeitsstunden() {
        final var mitarbeiter = this.belegschaft.alleMitarbeiter().iterator().next();
        final var heute = this.uhr.heute();

        final var year = Year.from(heute);
        final var month = heute.getMonth();
        final var arbeitsstunden = this.zeiterfassung.arbeitsstundenFür(mitarbeiter, year, month);
        this.lohnsoftware.schreibeArbeitsstundenFÜr(mitarbeiter, year, month, arbeitsstunden);
    }

}
