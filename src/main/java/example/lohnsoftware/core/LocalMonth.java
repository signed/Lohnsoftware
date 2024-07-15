package example.lohnsoftware.core;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Optional;

public record LocalMonth(Year year, Month month) {
    public static LocalMonth from(LocalDate localDate) {
        return new LocalMonth(Year.from(localDate), Month.from(localDate));
    }

    public static LocalMonth Erstelle(int jahr, int monat) {
        return Parse(jahr, monat).orElseThrow();
    }

    public static Optional<LocalMonth> Parse(int jahr, int monat) {
        final var year = jahr(jahr);
        final var month = getMonth(monat);
        if (year.isEmpty() || month.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new LocalMonth(year.get(), month.get()));
    }

    private static Optional<Year> jahr(int jahr) {
        try {
            return Optional.of(Year.of(jahr));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static Optional<Month> getMonth(int monat) {
        try {
            return Optional.ofNullable(Month.of(monat));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
