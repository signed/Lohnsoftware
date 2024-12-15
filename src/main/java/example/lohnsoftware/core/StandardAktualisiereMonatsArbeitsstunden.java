package example.lohnsoftware.core;

import io.vavr.control.Either;

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
    public Either<Fehler, String> aktualisiere(MonatsArbeitsstunden monatsArbeitsstunden) {
        /* fachliche validierung und andere Logik
         * - Korrektur in der Zeiterfassung nach abgeschlossener Monatsabrechnung
         * - Mehrstunden erst in Folgemonaten Abrechnen (wenn das legal ist)
         * - ...
         */
        if (!this.belegschaft.istAngestellt(monatsArbeitsstunden.mitarbeiter(), monatsArbeitsstunden.month())) {
            return Fehler.unbekannterMitarbeiter("Arbeiter hier nicht");
        }

        final var ergebnis = this.arbeitszeitkonto.erfasse(monatsArbeitsstunden);

        if (ergebnis.isRight()) {
            return AktualisiereMonatsArbeitsstunden.erfolg("Daten die der Aufrufer als Antwort braucht");
        }
        return Fehler.fehlschlag("Daten die der Aufrufer bei einem Fehlschlag braucht");
    }
}
