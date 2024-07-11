package example.lohnsoftware.core;

import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LohnsoftwareFake implements Lohnsoftware {
    private record Key(Mitarbeiter mitarbeiter, LocalMonth month) {
    }

    private final Map<Key, Arbeitsstunden> map = new HashMap<>();

    public Arbeitsstunden gearbeiteteStunden(Mitarbeiter mitarbeiter, LocalMonth month) {
        final var arbeitsstunden = this.map.get(new Key(mitarbeiter, month));
        return Objects.requireNonNullElseGet(arbeitsstunden, Arbeitsstunden::KeineArbeitsstunden);

    }

    @Override
    public void schreibeArbeitsstundenFÜr(Mitarbeiter mitarbeiter, Year year, Month month, Arbeitsstunden arbeitsstunden) {
        map.put(new Key(mitarbeiter, new LocalMonth(year, month)), arbeitsstunden);
    }
}
