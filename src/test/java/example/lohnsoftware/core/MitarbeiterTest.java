package example.lohnsoftware.core;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

import org.junit.jupiter.api.Test;

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
