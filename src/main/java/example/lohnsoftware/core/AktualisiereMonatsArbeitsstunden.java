package example.lohnsoftware.core;

import java.util.Optional;

public interface AktualisiereMonatsArbeitsstunden {
    Ergebnis aktualisiere(MonatsArbeitsstunden monatsArbeitsstunden);

    record Ergebnis(Optional<String> erfolg, Optional<String> fehlschlag) {
        public static Ergebnis erfolg(String nachricht) {
            return new Ergebnis(Optional.of(nachricht), Optional.empty());
        }

        public static Ergebnis fehlschlag(String nachricht) {
            return new Ergebnis(Optional.empty(), Optional.of(nachricht));
        }
    }
}
