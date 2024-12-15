package example.lohnsoftware.core;

import io.vavr.control.Either;

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
    public Either<Fehler, String> aktualisiere(MonatsArbeitsstunden monatsArbeitsstunden) {
        if (!erfolg) {
            return Fehler.fehlschlag("Hat nicht funktioniert");
        }
        if (unbekannteMitarbeiter.contains(monatsArbeitsstunden.mitarbeiter().personalNummer())) {
            return Fehler.unbekannterMitarbeiter("Arbeitet hier nicht");
        }
        map.put(new Key(monatsArbeitsstunden.mitarbeiter(), monatsArbeitsstunden.month()), monatsArbeitsstunden.arbeitsstunden());
        return AktualisiereMonatsArbeitsstunden.erfolg("");
    }

    public void unbekannterMitarbeiter(String mitarbeiterNummer) {
        this.unbekannteMitarbeiter.add(PersonalNummer.erstelle(mitarbeiterNummer));
    }

    public void wirdFehlschlagen(){
        this.erfolg = false;
    }
}
