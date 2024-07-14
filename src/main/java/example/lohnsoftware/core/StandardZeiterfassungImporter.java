package example.lohnsoftware.core;

public class StandardZeiterfassungImporter implements ZeiterfassungImporter {

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
        final var month = LocalMonth.from(this.uhr.heute());
        this.belegschaft.alleMitarbeiter().forEach(mitarbeiter -> importiereArbeitsstundenFür(mitarbeiter, month));
    }

    private void importiereArbeitsstundenFür(Mitarbeiter mitarbeiter, LocalMonth month) {
        final var arbeitsstunden = this.zeiterfassung.arbeitsstundenFür(mitarbeiter, month);
        final var monatsArbeitsstunden = new MonatsArbeitsstunden(month, mitarbeiter, arbeitsstunden);
        this.aktualisiereMonatsArbeitsstunden.aktualisiere(monatsArbeitsstunden);
    }

}
