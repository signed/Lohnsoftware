package example.lohnsoftware.core;

import io.vavr.control.Either;

import java.util.Optional;

import static example.lohnsoftware.lang.Converter.optionalFrom;

public record Mitarbeiter(PersonalNummer personalNummer) {

    public static Mitarbeiter Erstelle(String nummer) {
        return ParseOld(nummer).orElseThrow();
    }

    public static Optional<Mitarbeiter> ParseOld(String nummer) {
        return optionalFrom(Parse(nummer));
    }

    public static Either<Void, Mitarbeiter> Parse(String nummer) {
        final var personalNummer = PersonalNummer.Parse(nummer);
        if (personalNummer.isEmpty()) {
            return Either.left(null);
        }
        return Either.right(new Mitarbeiter(personalNummer.orElseThrow()));
    }
}
