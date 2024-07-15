package example.lohnsoftware.core;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Optional;

public record LocalMonth(Year year, Month month) {
    public static LocalMonth from(LocalDate localDate) {
        return new LocalMonth(Year.from(localDate), Month.from(localDate));
    }

    public static Optional<LocalMonth> Parse(int jahr, int monat) {
        final var year = jahr(jahr);
        if (year.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new LocalMonth(year.get(), Month.of(monat)));
    }

    private static Optional<Year> jahr(int jahr) {
        try {
            return Optional.of(Year.of(jahr));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
