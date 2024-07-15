package example.lohnsoftware.core;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class ZeiterfassungFake implements Zeiterfassung {

    private record Key(Mitarbeiter mitarbeiter, LocalMonth month) {
    }

    private final Map<Key, Arbeitsstunden> arbeitsstunden = new HashMap<>();

    @Override
    public Arbeitsstunden arbeitsstundenFür(Mitarbeiter mitarbeiter, LocalMonth month) {
        final var arbeitsstunden = this.arbeitsstunden.get(new Key(mitarbeiter, month));
        if (arbeitsstunden == null) {
            return Arbeitsstunden.KeineArbeitsstunden();
        }
        return arbeitsstunden;
    }

    public void arbeitet(Mitarbeiter mitarbeiter, LocalDate date, Arbeitsstunden dauer) {
        final var month = new LocalMonth(Year.from(date), Month.from(date));
        this.arbeitsstunden.put(new Key(mitarbeiter, month), dauer);
    }
}
