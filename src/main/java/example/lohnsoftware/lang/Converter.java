package example.lohnsoftware.lang;

import io.vavr.control.Either;

import java.util.Optional;

public class Converter {
    public static <T> Either<Void, T> eitherFrom(Optional<T> optional) {
        return optional.<Either<Void, T>>map(Either::right).orElseGet(() -> Either.left(null));
    }

    public static <T> Optional<T> optionalFrom(final Either<?, T> either) {
        if (either.isLeft()) {
            return Optional.empty();
        }
        return Optional.of(either.get());
    }
}
