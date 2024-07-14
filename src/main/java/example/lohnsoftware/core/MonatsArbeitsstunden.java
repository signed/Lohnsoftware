package example.lohnsoftware.core;

/**
 * Warum nur einmal Arbeitsstunden für den ganzen Monat an Stelle von einmal Arbeitsstunden für jeden Tag
 * - Meiner Einschätzung nach werden die Tagesinformation zur Lohnzahlung nicht gebrauch (könnte falsch sein)
 * - Tages genaue Arbeitsstunden stehen in der Zeiterfassung, warum in die Lohnsoftware duplizieren
 * - Einfacher zu implementieren
 */
public record MonatsArbeitsstunden(LocalMonth month, Mitarbeiter mitarbeiter, Arbeitsstunden arbeitsstunden) {
}
