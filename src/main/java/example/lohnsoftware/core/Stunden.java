package example.lohnsoftware.core;

import java.util.Optional;

public record Stunden(int wert) {
    public static Stunden Erstelle(int wert) {
        return Parse(wert).orElseThrow();
    }

    public static Optional<Stunden> Parse(int wert) {
        if (wert < 0) {
            return Optional.empty();
        }
        return Optional.of(new Stunden(wert));
    }
}
