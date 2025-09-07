package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Uhr;

import java.time.LocalDate;
import java.time.ZoneId;

public class SystemUhr implements Uhr {
    @Override
    public LocalDate heute() {
        return LocalDate.now(ZoneId.systemDefault());
    }
}
