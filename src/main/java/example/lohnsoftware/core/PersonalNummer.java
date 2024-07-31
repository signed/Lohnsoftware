package example.lohnsoftware.core;

import java.util.Optional;

public record PersonalNummer(String wert) {
    public static Optional<PersonalNummer> Parse(String nummer) {
        if (nummer == null) {
            return Optional.empty();
        }
        final var trimmedNummer = nummer.trim();
        if (trimmedNummer.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new PersonalNummer(trimmedNummer));
    }
}
