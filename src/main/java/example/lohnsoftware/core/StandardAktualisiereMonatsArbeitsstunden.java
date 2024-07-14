package example.lohnsoftware.core;

/**
 * Wiederverwendbare Geschäftslogik zum Schreiben von MonatsArbeitsstunden
 */
public class StandardAktualisiereMonatsArbeitsstunden implements AktualisiereMonatsArbeitsstunden {
    private final MonatsArbeitsstundenSchreiber monatsArbeitsstundenSchreiber;

    public StandardAktualisiereMonatsArbeitsstunden(MonatsArbeitsstundenSchreiber monatsArbeitsstundenSchreiber) {
        this.monatsArbeitsstundenSchreiber = monatsArbeitsstundenSchreiber;
    }

    @Override
    public Ergebnis aktualisiere(MonatsArbeitsstunden monatsArbeitsstunden) {
        /* fachliche validierung und andere Logik
         * - Korrektur in der Zeiterfassung nach abgeschlossener Monatsabrechnung
         * - Mehrstunden erst in Folgemonaten Abrechnen (wenn das legal ist)
         * - ...
        */
        final var ergebnis = this.monatsArbeitsstundenSchreiber.schreibe(monatsArbeitsstunden);
        if (ergebnis.erfolg().isPresent()) {
            return Ergebnis.erfolg("Daten die der Aufrufer als Antwort braucht");
        }
        return Ergebnis.fehlschlag("Daten die der Aufrufer bei einem Fehlschlag braucht");
    }
}
