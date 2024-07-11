package example.lohnsoftware.core;

import java.util.Optional;

public record Stunden(int wert) {
    public static Optional<Stunden> Erstelle(int wert) {
        if (wert < 0) {
            return Optional.empty();
        }
        return Optional.of(new Stunden(wert));
    }
}
