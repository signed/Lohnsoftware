package example.lohnsoftware.core;

import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record PersonalNummer(String wert) {

    public static PersonalNummer erstelle(String mitarbeiterNummer) {
        return parse(mitarbeiterNummer).orElseThrow();
    }

    public static Optional<PersonalNummer> parse(@Nullable String nummer) {
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
