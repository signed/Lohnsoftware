package example.lohnsoftware.core;

public record Arbeitsstunden(Stunden stunden, Minuten minuten) {

    public static Arbeitsstunden KeineArbeitsstunden() {
        return new Arbeitsstunden(new Stunden(0), new Minuten(0));
    }

    public static Arbeitsstunden Erstelle(int stundenWert, int minutenWert) {
        final var stunden = Stunden.Erstelle(stundenWert);
        final var minuten = Minuten.Erstelle(minutenWert);
        return new Arbeitsstunden(stunden, minuten);
    }

}
