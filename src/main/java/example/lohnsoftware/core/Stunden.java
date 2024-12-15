package example.lohnsoftware.core;

import io.vavr.control.Either;

import static example.lohnsoftware.lang.Converter.noSuchElement;

public record Stunden(int wert) {
    public static Stunden erstelle(int wert) {
        return parse(wert).getOrElseThrow(noSuchElement());
    }

    public static Either<Void, Stunden> parse(int wert) {
        if (wert < 0) {
            return Either.left(null);
        }
        return Either.right(new Stunden(wert));
    }
}
