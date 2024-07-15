package example.lohnsoftware.core;

/**
 * Wiederverwendbare Geschäftslogik zum Schreiben von MonatsArbeitsstunden
 */
public class StandardAktualisiereMonatsArbeitsstunden implements AktualisiereMonatsArbeitsstunden {
    private final Belegschaft belegschaft;
    private final Arbeitszeitkonto arbeitszeitkonto;

    public StandardAktualisiereMonatsArbeitsstunden(Belegschaft belegschaft, Arbeitszeitkonto arbeitszeitkonto) {
        this.belegschaft = belegschaft;
        this.arbeitszeitkonto = arbeitszeitkonto;
    }

    @Override
    public Ergebnis aktualisiere(MonatsArbeitsstunden monatsArbeitsstunden) {
        /* fachliche validierung und andere Logik
         * - Korrektur in der Zeiterfassung nach abgeschlossener Monatsabrechnung
         * - Mehrstunden erst in Folgemonaten Abrechnen (wenn das legal ist)
         * - ...
         */
        if (!this.belegschaft.istAngestellt(monatsArbeitsstunden.mitarbeiter(), monatsArbeitsstunden.month())) {
            return Ergebnis.unbekannterMitarbeiter("Arbeiter hier nicht");
        }

        final var ergebnis = this.arbeitszeitkonto.erfasse(monatsArbeitsstunden);

        if (ergebnis.erfolg().isPresent()) {
            return Ergebnis.erfolg("Daten die der Aufrufer als Antwort braucht");
        }
        return Ergebnis.fehlschlag("Daten die der Aufrufer bei einem Fehlschlag braucht");
    }
}
