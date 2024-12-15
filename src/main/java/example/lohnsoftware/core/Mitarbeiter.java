package example.lohnsoftware.core;

import example.lohnsoftware.lang.Converter;
import io.vavr.control.Either;

import java.util.Optional;

import static example.lohnsoftware.lang.Converter.noSuchElement;
import static example.lohnsoftware.lang.Converter.optionalFrom;

public record Mitarbeiter(PersonalNummer personalNummer) {

    public static Mitarbeiter Erstelle(String nummer) {
        return Parse(nummer).getOrElseThrow(noSuchElement());
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
