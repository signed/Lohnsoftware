package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static example.lohnsoftware.lang.Converter.optionalFrom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class MitarbeiterTest {

    @Test
    void parseGültigeMitarbeiterNummer() {
        assertThat(Mitarbeiter.Parse("korrekt")).containsOnRight(Mitarbeiter.Erstelle("korrekt"));
    }

    @Test
    void entferneLeerzeichenAmAnfangUndAmEndeDerNummer() {
        assertThat(Mitarbeiter.Parse(" korrekt ")).containsOnRight(Mitarbeiter.Erstelle("korrekt"));
    }

    @Test
    void nummerDarfNichtLeerSein() {
        assertThat(Mitarbeiter.Parse("")).isLeft();
        assertThat(Mitarbeiter.Parse("    ")).isLeft();
    }

    @Test
    void nummerDarfNichtNullSein() {
        assertThat(Mitarbeiter.Parse(null)).isLeft();
    }
}
