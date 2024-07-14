package example.lohnsoftware.core;

import java.time.LocalDate;

public class SystemUhr implements Uhr {
    @Override
    public LocalDate heute() {
        return LocalDate.now();
    }
}
