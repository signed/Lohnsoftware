package example.lohnsoftware.core;

import java.util.Optional;

public record Minuten(int wert) {
    public static Minuten Erstelle(int wert) {
        return Parse(wert).orElseThrow();
    }

    public static Optional<Minuten> Parse(int wert) {
        if (wert >= 60 || wert < 0) {
            return Optional.empty();
        }
        return Optional.of(new Minuten(wert));
    }
}
