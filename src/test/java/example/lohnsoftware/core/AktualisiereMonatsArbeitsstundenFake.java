package example.lohnsoftware.core;

import java.util.*;

public class AktualisiereMonatsArbeitsstundenFake implements AktualisiereMonatsArbeitsstunden {
    private record Key(Mitarbeiter mitarbeiter, LocalMonth month) {

    }
    private final Map<Key, Arbeitsstunden> map = new HashMap<>();
    private final List<PersonalNummer> unbekannteMitarbeiter = new ArrayList<>();
    private boolean erfolg = true;

    public Arbeitsstunden gearbeiteteStunden(Mitarbeiter mitarbeiter, LocalMonth month) {
        final var arbeitsstunden = this.map.get(new Key(mitarbeiter, month));
        return Objects.requireNonNullElseGet(arbeitsstunden, Arbeitsstunden::KeineArbeitsstunden);
    }

    @Override
    public Ergebnis aktualisiere(MonatsArbeitsstunden monatsArbeitsstunden) {
        if (!erfolg) {
            return Ergebnis.fehlschlag("Hat nicht funktioniert");
        }
        if (unbekannteMitarbeiter.contains(monatsArbeitsstunden.mitarbeiter().personalNummer())) {
            return Ergebnis.unbekannterMitarbeiter("Arbeitet hier nicht");
        }
        map.put(new Key(monatsArbeitsstunden.mitarbeiter(), monatsArbeitsstunden.month()), monatsArbeitsstunden.arbeitsstunden());
        return Ergebnis.erfolg("");
    }

    public void unbekannterMitarbeiter(String mitarbeiterNummer) {
        this.unbekannteMitarbeiter.add(PersonalNummer.Erstelle(mitarbeiterNummer));
    }

    public void wirdFehlschlagen(){
        this.erfolg = false;
    }
}
