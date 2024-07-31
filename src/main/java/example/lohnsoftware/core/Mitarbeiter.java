package example.lohnsoftware.core;

import java.util.Optional;

public record Mitarbeiter(String personalNummer) {

    public static Mitarbeiter Erstelle(String nummer) {
        return Parse(nummer).orElseThrow();
    }

    public static Optional<Mitarbeiter> Parse(String nummer) {
        final var personalNummer = PersonalNummer.Parse(nummer);
        if (personalNummer.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Mitarbeiter(personalNummer.orElseThrow().wert()));
    }
}
