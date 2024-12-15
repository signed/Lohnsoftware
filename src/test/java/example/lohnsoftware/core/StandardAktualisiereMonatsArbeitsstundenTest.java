package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

class StandardAktualisiereMonatsArbeitsstundenTest {

    final BelegschaftFake belegschaft = new BelegschaftFake();
    final Arbeitszeitkonto notAccessed = null;

    @Test
    void prüfeObDerMitarbeiterBekanntIst() {
        final var mitarbeiter = Mitarbeiter.erstelle("unbekannt");
        final var monat = LocalMonth.erstelle(2000, 8);
        final var arbeitsstunden = Arbeitsstunden.erstelle(7, 42);
        final var stunden = new MonatsArbeitsstunden(monat, mitarbeiter, arbeitsstunden);
        final var flow = new StandardAktualisiereMonatsArbeitsstunden(belegschaft, notAccessed);
        belegschaft.nichtBekannter(mitarbeiter);

        final var ergebnis = flow.aktualisiere(stunden);
        assertThat(ergebnis).containsLeftInstanceOf(AktualisiereMonatsArbeitsstunden.UnbekannterMitarbeiter.class);
    }

}
