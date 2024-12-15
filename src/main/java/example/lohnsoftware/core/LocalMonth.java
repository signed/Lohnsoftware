package example.lohnsoftware.core;

import example.lohnsoftware.lang.Converter;
import io.vavr.collection.Seq;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;

public record LocalMonth(Year year, Month month) {
    public static LocalMonth from(LocalDate localDate) {
        return new LocalMonth(Year.from(localDate), Month.from(localDate));
    }

    public static LocalMonth Erstelle(int jahr, int monat) {
        return ParseOld(jahr, monat).orElseThrow();
    }

    public static Optional<LocalMonth> ParseOld(int jahr, int monat) {
        return Converter.optionalFrom(Parse(jahr, monat));
    }

    public static Either<Void,LocalMonth> Parse(int jahr, int monat) {
        final var year = jahr(jahr);
        final var month = getMonth(monat);
        final var sequence =  Either.sequence(List.of(year, month));

        if (sequence.isLeft()) {
            return Either.left(null);
        }
        return Either.right(new LocalMonth(year.get(), month.get()));
    }

    private static Either<Void,Year> jahr(int jahr) {
        try {
            return Either.right(Year.of(jahr));
        } catch (Exception e) {
            return Either.left(null);
        }
    }

    private static Either<Void,Month> getMonth(int monat) {
        try {
            return Either.right(Month.of(monat));
        } catch (Exception e) {
            return Either.left(null);
        }
    }
}
