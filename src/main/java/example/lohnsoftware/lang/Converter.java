package example.lohnsoftware.lang;

import io.vavr.control.Either;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

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

    /**
     * Same Exception you get on <code>{@link Optional#orElseThrow()}</code>
     */
    public static Supplier<NoSuchElementException> noSuchElement() {
        return () -> new NoSuchElementException("No value present");
    }
}
