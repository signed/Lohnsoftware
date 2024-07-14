package example.lohnsoftware;

import example.lohnsoftware.core.MonatsArbeitsstunden;
import example.lohnsoftware.core.SchreibeMonatsArbeitsstunden;

/**
 * Wiederverwendbare Geschäftslogik zum Schreiben von MonatsArbeitsstunden, z.B.
 * - Korrektur in der Zeiterfassung nach abgeschlossener Monatsabrechnung
 * - Mehrstunden erst in Folgemonaten Abrechnen (wenn das legal ist)
 * - ...
 */
public class StandardSchreibeMonatsArbeitsstunden implements SchreibeMonatsArbeitsstunden {
    @Override
    public Ergebnis schreibe(MonatsArbeitsstunden monatsArbeitsstunden) {
        //todo implement
        return Ergebnis.erfolg("");
    }
}
