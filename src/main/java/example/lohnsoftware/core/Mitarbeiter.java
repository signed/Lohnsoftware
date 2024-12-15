package example.lohnsoftware.core;

import io.vavr.control.Either;

import static example.lohnsoftware.lang.Converter.noSuchElement;

public record Mitarbeiter(PersonalNummer personalNummer) {

    public static Mitarbeiter Erstelle(String nummer) {
        return Parse(nummer).getOrElseThrow(noSuchElement());
    }

    public static Either<Void, Mitarbeiter> Parse(String nummer) {
        final var personalNummer = PersonalNummer.Parse(nummer);
        if (personalNummer.isEmpty()) {
            return Either.left(null);
        }
        return Either.right(new Mitarbeiter(personalNummer.orElseThrow()));
    }
}
