package example.lohnsoftware.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BelegschaftFake implements Belegschaft {
    private final HashSet<Mitarbeiter> alleMitarbeitenden = new HashSet<>();

    @Override
    public Set<Mitarbeiter> alleMitarbeiter() {
        return Collections.unmodifiableSet(alleMitarbeitenden);
    }

    @Override
    public boolean istAngestellt(Mitarbeiter mitarbeiter, LocalMonth month) {
        return alleMitarbeitenden.contains(mitarbeiter);
    }

    public void einstellen(Mitarbeiter... mitarbeiter) {
        this.alleMitarbeitenden.addAll(Arrays.stream(mitarbeiter).toList());
    }

    public void nichtBekannter(Mitarbeiter unbekannt) {
        this.alleMitarbeitenden.remove(unbekannt);
    }
}
