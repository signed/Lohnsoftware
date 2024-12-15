package example.lohnsoftware.core;

import io.vavr.control.Either;


public interface AktualisiereMonatsArbeitsstunden {

    static Either<Fehler, String> erfolg(String nachricht) {
        return Either.right(nachricht);
    }

    sealed interface Fehler permits UnbekannterMitarbeiter, Generisch {
        static Either<Fehler, String> fehlschlag(String nachricht) {
            return Either.left(new Generisch(nachricht));
        }

        static Either<Fehler, String> unbekannterMitarbeiter(String nachricht) {
            return Either.left(new UnbekannterMitarbeiter(nachricht));
        }
    }

    record UnbekannterMitarbeiter(String mitarbeiter) implements Fehler {
    }

    record Generisch(String nachricht) implements Fehler {
    }

    Either<Fehler, String> aktualisiere(MonatsArbeitsstunden monatsArbeitsstunden);
}
