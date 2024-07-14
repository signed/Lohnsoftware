package example.lohnsoftware.core;

import java.util.Optional;

public interface SchreibeMonatsArbeitsstunden {
    Ergebnis schreib(MonatsArbeitsstunden monatsArbeitsstunden);

    record Ergebnis(Optional<String> erfolg) {
        public static Ergebnis erfolg(String nachricht) {
            return new Ergebnis(Optional.of(nachricht));
        }
    }
}
