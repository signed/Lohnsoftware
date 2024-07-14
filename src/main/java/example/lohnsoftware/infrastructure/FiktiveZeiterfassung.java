package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Arbeitsstunden;
import example.lohnsoftware.core.LocalMonth;
import example.lohnsoftware.core.Mitarbeiter;
import example.lohnsoftware.core.Zeiterfassung;

public class FiktiveZeiterfassung implements Zeiterfassung {
    @Override
    public Arbeitsstunden arbeitsstundenFür(Mitarbeiter mitarbeiter, LocalMonth month) {
        //todo implement
        return Arbeitsstunden.KeineArbeitsstunden();
    }
}
