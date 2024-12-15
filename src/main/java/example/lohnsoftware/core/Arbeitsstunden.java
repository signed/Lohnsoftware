package example.lohnsoftware.core;

import example.lohnsoftware.lang.Converter;
import io.vavr.control.Either;

import java.util.Optional;

public record Arbeitsstunden(Stunden stunden, Minuten minuten) {

    public static Arbeitsstunden KeineArbeitsstunden() {
        return new Arbeitsstunden(new Stunden(0), new Minuten(0));
    }

    public static Arbeitsstunden Erstelle(int stundenWert, int minutenWert) {
        return Arbeitsstunden.ParseOld(stundenWert, minutenWert).orElseThrow();
    }

    public static Optional<Arbeitsstunden> ParseOld(int stundenWert, int minutenWert) {
        return Converter.optionalFrom(Parse(stundenWert, minutenWert));
    }

    public static Either<Void,Arbeitsstunden> Parse(int stundenWert, int minutenWert) {
        final var stunden = Stunden.Parse(stundenWert);
        final var minuten = Minuten.Parse(minutenWert);
        if (stunden.isEmpty() || minuten.isEmpty()) {
            return Either.left(null);
        }
        return Either.right(new Arbeitsstunden(stunden.get(), minuten.get()));
    }

}
