package example.lohnsoftware.core;

/**
 * Warum nur einmal Arbeitsstunden für den ganzen Monat an Stelle von einmal Arbeitsstunden für jeden Tag
 * <ul>
 *     <li>Meiner Einschätzung nach werden die Tagesinformation zur Lohnzahlung nicht gebrauch (könnte falsch sein)</li>
 *     <li>Tages genaue Arbeitsstunden stehen in der Zeiterfassung, warum in die Lohnsoftware duplizieren</li>
 *     <li>Einfacher zu implementieren</li>
 * </ul>
 */
public record MonatsArbeitsstunden(LocalMonth month, Mitarbeiter mitarbeiter, Arbeitsstunden arbeitsstunden) {
}
