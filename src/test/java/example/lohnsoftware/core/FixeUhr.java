package example.lohnsoftware.core;

import java.time.LocalDate;

public class FixeUhr implements Uhr {
    private final LocalDate fix;

    public FixeUhr(final LocalDate fix) {
        this.fix = fix;
    }

    @Override
    public LocalDate heute() {

        return fix;
    }
}
