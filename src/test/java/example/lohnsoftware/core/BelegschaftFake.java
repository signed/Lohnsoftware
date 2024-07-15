package example.lohnsoftware.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BelegschaftFake implements Belegschaft {
    private final HashSet<Mitarbeiter> alleMitarbeitenden = new HashSet<Mitarbeiter>();

    @Override
    public Set<Mitarbeiter> alleMitarbeiter() {
        return Collections.unmodifiableSet(alleMitarbeitenden);
    }

    public void einstellen(Mitarbeiter... mitarbeiter) {
        this.alleMitarbeitenden.addAll(Arrays.stream(mitarbeiter).toList());
    }
}
