package example.lohnsoftware.core;

import java.util.Set;

public interface Belegschaft {
    Set<Mitarbeiter> alleMitarbeiter();

    boolean istAngestellt(Mitarbeiter mitarbeiter, LocalMonth month);
}
