package example.lohnsoftware.core;

import io.vavr.control.Either;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

import static example.lohnsoftware.lang.Converter.noSuchElement;

public record LocalMonth(Year year, Month month) {
    public static LocalMonth from(LocalDate localDate) {
        return new LocalMonth(Year.from(localDate), Month.from(localDate));
    }

    public static LocalMonth erstelle(int jahr, int monat) {
        return parse(jahr, monat).getOrElseThrow(noSuchElement());
    }

    public static Either<Void,LocalMonth> parse(int jahr, int monat) {
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
