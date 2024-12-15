package example.lohnsoftware.core;

import io.vavr.control.Either;

import static example.lohnsoftware.lang.Converter.noSuchElement;

public record Arbeitsstunden(Stunden stunden, Minuten minuten) {

    public static Arbeitsstunden KeineArbeitsstunden() {
        return new Arbeitsstunden(new Stunden(0), new Minuten(0));
    }

    public static Arbeitsstunden erstelle(int stundenWert, int minutenWert) {
        return parse(stundenWert, minutenWert).getOrElseThrow(noSuchElement());
    }

    public static Either<Void,Arbeitsstunden> parse(int stundenWert, int minutenWert) {
        final var stunden = Stunden.parse(stundenWert);
        final var minuten = Minuten.parse(minutenWert);
        if (stunden.isEmpty() || minuten.isEmpty()) {
            return Either.left(null);
        }
        return Either.right(new Arbeitsstunden(stunden.get(), minuten.get()));
    }

}
