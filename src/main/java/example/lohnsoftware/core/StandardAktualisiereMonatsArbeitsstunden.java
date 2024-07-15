package example.lohnsoftware.core;

/**
 * Wiederverwendbare Geschäftslogik zum Schreiben von MonatsArbeitsstunden
 */
public class StandardAktualisiereMonatsArbeitsstunden implements AktualisiereMonatsArbeitsstunden {
    private final Arbeitszeitkonto arbeitszeitkonto;

    public StandardAktualisiereMonatsArbeitsstunden(Belegschaft belegschaft, Arbeitszeitkonto arbeitszeitkonto) {
        this.arbeitszeitkonto = arbeitszeitkonto;
    }

    @Override
    public Ergebnis aktualisiere(MonatsArbeitsstunden monatsArbeitsstunden) {
        /* fachliche validierung und andere Logik
         * - Korrektur in der Zeiterfassung nach abgeschlossener Monatsabrechnung
         * - Mehrstunden erst in Folgemonaten Abrechnen (wenn das legal ist)
         * - ...
         */
        final var ergebnis = this.arbeitszeitkonto.erfasse(monatsArbeitsstunden);
        if (ergebnis.erfolg().isPresent()) {
            return Ergebnis.erfolg("Daten die der Aufrufer als Antwort braucht");
        }
        //TODO unbekannter Mitarbeiter
        return Ergebnis.fehlschlag("Daten die der Aufrufer bei einem Fehlschlag braucht");
    }
}
