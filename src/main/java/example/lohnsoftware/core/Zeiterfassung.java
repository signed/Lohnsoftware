package example.lohnsoftware.core;

import java.util.Optional;

public interface Zeiterfassung {
    Optional<Arbeitsstunden> arbeitsstundenFür(Mitarbeiter mitarbeiter, LocalMonth month);
}
