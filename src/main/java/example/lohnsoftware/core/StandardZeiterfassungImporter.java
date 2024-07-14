package example.lohnsoftware.core;

public class StandardZeiterfassungImporter implements ZeiterfassungImporter {

    private final Zeiterfassung zeiterfassung;
    private final SchreibeMonatsArbeitsstunden schreibeMonatsArbeitsstunden;
    private final Belegschaft belegschaft;
    private final Uhr uhr;

    public StandardZeiterfassungImporter(Zeiterfassung zeiterfassung, SchreibeMonatsArbeitsstunden schreibeMonatsArbeitsstunden, Belegschaft belegschaft, Uhr uhr) {
        this.zeiterfassung = zeiterfassung;
        this.schreibeMonatsArbeitsstunden = schreibeMonatsArbeitsstunden;
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
        final var monatsArbeitsstunden = new MonatsArbeitsstunden(mitarbeiter, month, arbeitsstunden);
        this.schreibeMonatsArbeitsstunden.schreibe(monatsArbeitsstunden);
    }

}
