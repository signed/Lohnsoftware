package example.lohnsoftware.core;

public record Arbeitsstunden(Stunden stunden, Minuten minuten) {

    public static Arbeitsstunden KeineArbeitsstunden() {
        return new Arbeitsstunden(new Stunden(0), new Minuten(0));
    }

    public static Arbeitsstunden Dauer(int stunden, int minuten) {
        final var h = Stunden.Erstelle(stunden);
        final var m = Minuten.Erstelle(minuten);
        if (h.isEmpty() || m.isEmpty()) {
            throw new RuntimeException("Keine Arbeitsstunden");
        }
        return new Arbeitsstunden(h.get(), m.get());
    }

}
