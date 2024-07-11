package example.lohnsoftware.core;

import java.time.LocalDate;
import java.time.Month;

public class LocalDateMother {

    public static LocalDate lastLocalDateOfAnyMonth() {
        return LocalDate.of(2024, Month.AUGUST, 12);
    }

    public static LocalDate anyDateInSameMonthBefore(LocalDate date) {
        if (date.getDayOfMonth() == 1) {
            throw new RuntimeException("there is not day before the 1st this month");
        }
        return date.minusDays(1);
    }
}
