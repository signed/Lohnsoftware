package example.lohnsoftware.core;

import java.util.Optional;

public interface AktualisiereMonatsArbeitsstunden {
    Ergebnis aktualisiere(MonatsArbeitsstunden monatsArbeitsstunden);

    record Ergebnis(Optional<String> erfolg) {
        public static Ergebnis erfolg(String nachricht) {
            return new Ergebnis(Optional.of(nachricht));
        }
    }
}
