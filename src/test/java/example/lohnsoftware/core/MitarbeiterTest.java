package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MitarbeiterTest {

    @Test
    void parseGültigeMitarbeiterNummer() {
        assertThat(Mitarbeiter.parse("korrekt")).hasValue(new Mitarbeiter("korrekt"));
    }

    @Test
    void entferneLeerzeichenAmAnfangUndAmEndeDerNummer() {
        assertThat(Mitarbeiter.parse(" korrekt ")).hasValue(new Mitarbeiter("korrekt"));
    }

    @Test
    void nummerDarfNichtLeerSein() {
        assertThat(Mitarbeiter.parse("")).isEmpty();
        assertThat(Mitarbeiter.parse("    ")).isEmpty();
    }

    @Test
    void nummerDarfNichtNullSein() {
        assertThat(Mitarbeiter.parse(null)).isEmpty();
    }
}
