package example.lohnsoftware.core;

import java.util.Optional;

public record Minuten(int wert) {
    public static Optional<Minuten> Erstelle(int wert) {
        if (wert >= 60 || wert < 0) {
            return Optional.empty();
        }
        return Optional.of(new Minuten(wert));
    }
}
