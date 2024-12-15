package example.lohnsoftware.core;

import io.vavr.control.Either;

import java.util.Optional;

public interface Arbeitszeitkonto {
    static Either<Fehler, String> erfolg(String nachricht) {
        return Either.right(nachricht);
    }

    sealed interface Fehler permits Generisch {
        static Either<Fehler, String> fehlschlag(String nachricht) {
            return Either.left(new Generisch(nachricht));
        }
    }

    record Generisch(String nachricht) implements Fehler {
    }

    Either<Fehler, String> erfasse(MonatsArbeitsstunden monatsArbeitsstunden);
}
