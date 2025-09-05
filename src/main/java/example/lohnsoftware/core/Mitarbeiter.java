package example.lohnsoftware.core;

import io.vavr.control.Either;
import org.jspecify.annotations.Nullable;

import static example.lohnsoftware.lang.Converter.noSuchElement;

public record Mitarbeiter(PersonalNummer personalNummer) {

    public static Mitarbeiter erstelle(String nummer) {
        return parse(nummer).getOrElseThrow(noSuchElement());
    }

    public static Either<Void, Mitarbeiter> parse(@Nullable String nummer) {
        final var personalNummer = PersonalNummer.parse( nummer);
        if (personalNummer.isEmpty()) {
            return Either.left(null);
        }
        return Either.right(new Mitarbeiter(personalNummer.orElseThrow()));
    }
}
