package example.lohnsoftware.core;

import java.time.LocalDate;
import java.time.Month;

public class LocalDateMother {

    public static LocalDate letztesLocalDateInIrgendeinemMonat() {
        return LocalDate.of(2024, Month.AUGUST, 12);
    }

    public static LocalDate irgendeinLocalDateImSelbenMonatVor(LocalDate date) {
        if (date.getDayOfMonth() == 1) {
            throw new RuntimeException("Es gibt ein LocalDate for dem Ersten eines Monats");
        }
        return date.minusDays(1);
    }
}
