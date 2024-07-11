package example.lohnsoftware.core;

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
        final var month = LocalMonth.from(this.uhr.heute());
        this.belegschaft.alleMitarbeiter().forEach(mitarbeiter -> importiereArbeitsstundenFür(mitarbeiter, month));
    }

    private void importiereArbeitsstundenFür(Mitarbeiter mitarbeiter, LocalMonth month) {
        final var arbeitsstunden = this.zeiterfassung.arbeitsstundenFür(mitarbeiter, month);
        this.lohnsoftware.schreibeArbeitsstundenFÜr(mitarbeiter, month, arbeitsstunden);
    }

}
