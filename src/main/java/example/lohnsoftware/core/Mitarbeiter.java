package example.lohnsoftware.core;

import java.util.Optional;

public record Mitarbeiter(String nummer) {

    public static Mitarbeiter Erstelle(String nummer) {
        return parse(nummer).orElseThrow();
    }

    public static Optional<Mitarbeiter> parse(String nummer) {
        if (nummer == null) {
            return Optional.empty();
        }
        final var trimmedNummer = nummer.trim();
        if (trimmedNummer.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Mitarbeiter(trimmedNummer));
    }
}
