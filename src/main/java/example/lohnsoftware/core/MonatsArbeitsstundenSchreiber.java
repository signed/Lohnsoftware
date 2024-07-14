package example.lohnsoftware.core;

import java.util.Optional;

public interface MonatsArbeitsstundenSchreiber {

    record Ergebnis(Optional<String> erfolg) {
        public static Ergebnis ergebnis(String nachricht) {
            return new Ergebnis(Optional.of(nachricht));
        }
    }

    Ergebnis schreibe(MonatsArbeitsstunden monatsArbeitsstunden);
}
