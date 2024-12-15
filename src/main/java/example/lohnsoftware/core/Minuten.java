package example.lohnsoftware.core;

import io.vavr.control.Either;

import static example.lohnsoftware.lang.Converter.noSuchElement;

public record Minuten(int wert) {
    public static Minuten erstelle(int wert) {
        return parse(wert).getOrElseThrow(noSuchElement());
    }

    public static Either<Void,Minuten> parse(int wert) {
        if (wert >= 60 || wert < 0) {
            return Either.left(null);
        }
        return Either.right(new Minuten(wert));
    }
}
