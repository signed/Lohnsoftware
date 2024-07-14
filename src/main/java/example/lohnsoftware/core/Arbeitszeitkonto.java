package example.lohnsoftware.core;

import java.util.Optional;

public interface Arbeitszeitkonto {

    record Ergebnis(Optional<String> erfolg) {
        public static Ergebnis ergebnis(String nachricht) {
            return new Ergebnis(Optional.of(nachricht));
        }
    }

    Ergebnis erfasse(MonatsArbeitsstunden monatsArbeitsstunden);
}
