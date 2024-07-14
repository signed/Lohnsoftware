package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Belegschaft;
import example.lohnsoftware.core.Mitarbeiter;

import java.util.Set;

public class JsonGestützteBelegschaft implements Belegschaft {
    @Override
    public Set<Mitarbeiter> alleMitarbeiter() {
        return Set.of();
    }
}
