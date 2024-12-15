package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class MitarbeiterTest {

    @Test
    void parseGültigeMitarbeiterNummer() {
        assertThat(Mitarbeiter.parse("korrekt")).containsOnRight(Mitarbeiter.erstelle("korrekt"));
    }

    @Test
    void entferneLeerzeichenAmAnfangUndAmEndeDerNummer() {
        assertThat(Mitarbeiter.parse(" korrekt ")).containsOnRight(Mitarbeiter.erstelle("korrekt"));
    }

    @Test
    void nummerDarfNichtLeerSein() {
        assertThat(Mitarbeiter.parse("")).isLeft();
        assertThat(Mitarbeiter.parse("    ")).isLeft();
    }

    @Test
    void nummerDarfNichtNullSein() {
        assertThat(Mitarbeiter.parse(null)).isLeft();
    }
}
