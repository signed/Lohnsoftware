package example.lohnsoftware.core;

import java.time.Month;
import java.time.Year;

public interface Lohnsoftware {
    //todo parameter count
    void schreibeArbeitsstundenFÜr(Mitarbeiter mitarbeiter, Year year, Month month, Arbeitsstunden arbeitsstunden);
}
