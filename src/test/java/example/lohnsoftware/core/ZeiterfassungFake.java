package example.lohnsoftware.core;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class ZeiterfassungFake implements Zeiterfassung {

    private record Key(Mitarbeiter mitarbeiter, Year year, Month month) {
    }

    private final Map<Key, Arbeitsstunden> arbeitsstunden = new HashMap<>();

    @Override
    public Arbeitsstunden arbeitsstundenFür(Mitarbeiter mitarbeiter, Year year, Month month) {
        final var arbeitsstunden = this.arbeitsstunden.get(new Key(mitarbeiter, year, month));
        if (arbeitsstunden == null) {
            return Arbeitsstunden.KeineArbeitsstunden();
        }
        return arbeitsstunden;
    }

    public void arbeitet(Mitarbeiter mitarbeiter, LocalDate date, Arbeitsstunden dauer) {
        this.arbeitsstunden.put(new Key(mitarbeiter, Year.from(date), Month.from(date)), dauer);
    }
}
