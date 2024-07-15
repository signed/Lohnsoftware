package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MitarbeiterTest {

    @Test
    void parseGültigeMitarbeiterNummer() {
        assertThat(Mitarbeiter.Parse("korrekt")).hasValue(Mitarbeiter.Erstelle("korrekt"));
    }

    @Test
    void entferneLeerzeichenAmAnfangUndAmEndeDerNummer() {
        assertThat(Mitarbeiter.Parse(" korrekt ")).hasValue(Mitarbeiter.Erstelle("korrekt"));
    }

    @Test
    void nummerDarfNichtLeerSein() {
        assertThat(Mitarbeiter.Parse("")).isEmpty();
        assertThat(Mitarbeiter.Parse("    ")).isEmpty();
    }

    @Test
    void nummerDarfNichtNullSein() {
        assertThat(Mitarbeiter.Parse(null)).isEmpty();
    }
}
