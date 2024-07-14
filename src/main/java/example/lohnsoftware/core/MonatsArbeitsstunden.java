package example.lohnsoftware.core;

/**
 * Warum nur ein Arbeitsstunden für den ganzen Monat an Stelle von ein Arbeitsstunden für jeden Tag
 * - Meiner Einschätzung werden die Tagesinformation zur Lohnzahlung nicht gebrauch (könnte falsch sein)
 * - Tages genaue Arbeitsstunden stehen in der Zeiterfassung
 * - Einfach zu implementieren
 *
 * @param month
 * @param mitarbeiter
 * @param arbeitsstunden
 */
public record MonatsArbeitsstunden(LocalMonth month, Mitarbeiter mitarbeiter, Arbeitsstunden arbeitsstunden) {
}
