package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Uhr;

import java.time.LocalDate;

public class SystemUhr implements Uhr {
    @Override
    public LocalDate heute() {
        return LocalDate.now();
    }
}
