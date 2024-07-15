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
        return Optional.of(new LocalMonth(Year.of(jahr), Month.of(monat)));
    }
}
