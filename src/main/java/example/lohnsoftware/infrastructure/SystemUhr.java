package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Uhr;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;

public class SystemUhr implements Uhr {
    @Override
    public LocalDate heute() {
        return LocalDate.now(ZoneId.systemDefault());
    }
}
