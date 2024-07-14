package example.lohnsoftware.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SchreibeMonatsArbeitsstundenFake implements SchreibeMonatsArbeitsstunden {
    private record Key(Mitarbeiter mitarbeiter, LocalMonth month) {
    }

    private final Map<Key, Arbeitsstunden> map = new HashMap<>();

    public Arbeitsstunden gearbeiteteStunden(Mitarbeiter mitarbeiter, LocalMonth month) {
        final var arbeitsstunden = this.map.get(new Key(mitarbeiter, month));
        return Objects.requireNonNullElseGet(arbeitsstunden, Arbeitsstunden::KeineArbeitsstunden);

    }

    @Override
    public SchreibeMonatsArbeitsstundenErgebnis schreib(MonatsArbeitsstunden monatsArbeitsstunden) {
        map.put(new Key(monatsArbeitsstunden.mitarbeiter(), monatsArbeitsstunden.month()), monatsArbeitsstunden.arbeitsstunden());
        return SchreibeMonatsArbeitsstundenErgebnis.erfolg("");
    }
}
