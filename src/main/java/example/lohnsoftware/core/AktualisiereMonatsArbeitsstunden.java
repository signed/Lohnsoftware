package example.lohnsoftware.core;

import java.util.Optional;

public interface AktualisiereMonatsArbeitsstunden {
    record Ergebnis(Optional<String> erfolg, Optional<String> fehlschlag, Optional<String> unbekannterMitarbeiter) {
        public static Ergebnis erfolg(String nachricht) {
            return new Ergebnis(Optional.of(nachricht), Optional.empty(), Optional.empty());
        }

        public static Ergebnis fehlschlag(String nachricht) {
            return new Ergebnis(Optional.empty(), Optional.of(nachricht), Optional.empty());
        }

        public static Ergebnis unbekannterMitarbeiter(String nachricht) {
            return new Ergebnis(Optional.empty(), Optional.empty(), Optional.of(nachricht));
        }
    }

    Ergebnis aktualisiere(MonatsArbeitsstunden monatsArbeitsstunden);
}
