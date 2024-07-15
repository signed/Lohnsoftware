package example.lohnsoftware.core;

import java.util.*;

public class AktualisiereMonatsArbeitsstundenFake implements AktualisiereMonatsArbeitsstunden {
    private record Key(Mitarbeiter mitarbeiter, LocalMonth month) {

    }
    private final Map<Key, Arbeitsstunden> map = new HashMap<>();
    private final List<String> unbekannteMitarbeiter = new ArrayList<>();

    public Arbeitsstunden gearbeiteteStunden(Mitarbeiter mitarbeiter, LocalMonth month) {
        final var arbeitsstunden = this.map.get(new Key(mitarbeiter, month));
        return Objects.requireNonNullElseGet(arbeitsstunden, Arbeitsstunden::KeineArbeitsstunden);
    }

    public void unbekannterMitarbeiter(String mitarbeiterNummer) {
        this.unbekannteMitarbeiter.add(mitarbeiterNummer);
    }

    @Override
    public Ergebnis aktualisiere(MonatsArbeitsstunden monatsArbeitsstunden) {
        if (unbekannteMitarbeiter.contains(monatsArbeitsstunden.mitarbeiter().nummer())) {
            return Ergebnis.unbekannterMitarbeiter("Arbeiter hier nicht");
        }
        map.put(new Key(monatsArbeitsstunden.mitarbeiter(), monatsArbeitsstunden.month()), monatsArbeitsstunden.arbeitsstunden());
        return Ergebnis.erfolg("");
    }
}
