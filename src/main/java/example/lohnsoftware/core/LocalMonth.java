package example.lohnsoftware.core;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public record LocalMonth(Year year, Month month) {
    public static LocalMonth from(LocalDate localDate) {
        return new LocalMonth(Year.from(localDate), Month.from(localDate));
    }
}
