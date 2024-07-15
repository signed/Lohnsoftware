package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StandardAktualisiereMonatsArbeitsstundenTest {

    final BelegschaftFake belegschaft = new BelegschaftFake();
    final Arbeitszeitkonto notAccessed = null;


    @Test
    void prüfeObDerMitarbeiterBekanntIst() {
        final var mitarbeiter = Mitarbeiter.Erstelle("unbekannt");
        final var monat = LocalMonth.Erstelle(2000, 8);
        final var arbeitsstunden = Arbeitsstunden.Erstelle(7, 42);
        final var stunden = new MonatsArbeitsstunden(monat, mitarbeiter, arbeitsstunden);
        final var flow = new StandardAktualisiereMonatsArbeitsstunden(belegschaft, notAccessed);
        belegschaft.nichtBekannter(mitarbeiter);

        final var ergebnis = flow.aktualisiere(stunden);
        assertThat(ergebnis.unbekannterMitarbeiter()).isPresent();
    }

}
