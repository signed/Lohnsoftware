package example.lohnsoftware.core;

import java.time.Month;
import java.time.Year;

public interface Zeiterfassung {
    Arbeitsstunden arbeitsstundenFür(Mitarbeiter mitarbeiter, Year year, Month month);
 }
