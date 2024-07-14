package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.LocalMonth;
import example.lohnsoftware.core.Mitarbeiter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FiktiveZeiterfassungJsonTest {

    @Test
    void leseArbeitszeitFürMitarbeiterDerBereitsArbeitszeitErfasstHat() {
        final var alice = new Mitarbeiter("Alice");
        final LocalMonth notAccessed = null;
        final var arbeitsstunden = new FiktiveZeiterfassung().arbeitsstundenFür(alice, notAccessed);

        assertThat(arbeitsstunden.stunden().wert()).isEqualTo(3);
        assertThat(arbeitsstunden.minuten().wert()).isEqualTo(7);
    }

    @Test
    void gipKeineArbeitsstundenZurückFürMitarbeiterDieNochKeineArbeitszeitErfasstHaben() {
        final var alice = new Mitarbeiter("NochKeineZeitErfasst");
        final LocalMonth notAccessed = null;
        final var arbeitsstunden = new FiktiveZeiterfassung().arbeitsstundenFür(alice, notAccessed);


        assertThat(arbeitsstunden.stunden().wert()).isEqualTo(0);
        assertThat(arbeitsstunden.minuten().wert()).isEqualTo(0);
    }

}
