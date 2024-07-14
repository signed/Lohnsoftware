package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Mitarbeiter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JsonGestützteBelegschaftTest {

    @Test
    void leseAlleMitarbeiterAusJsonDatei() {
        final var jsonGestützteBelegschaft = new JsonGestützteBelegschaft();
        final var mitarbeiter = jsonGestützteBelegschaft.alleMitarbeiter();
        assertThat(mitarbeiter).containsExactlyInAnyOrder(new Mitarbeiter("Alice"), new Mitarbeiter("Bob"), new Mitarbeiter("Carol"));
    }
}
