package example.lohnsoftware.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.lohnsoftware.core.Mitarbeiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class JsonBelegschaftTest {

    @TempDir
    private Path tempDir;

    @Test
    void leseAlleMitarbeiterAusJsonDatei() throws IOException {
        angestelltSind("Eins", "Zwei", "Drei");

        assertThat(alleMitarbeiter())
                .containsExactlyInAnyOrder(Mitarbeiter.erstelle("Eins"), Mitarbeiter.erstelle("Zwei"), Mitarbeiter.erstelle("Drei"));
    }

    private Set<Mitarbeiter> alleMitarbeiter() {
        final var jsonGestützteBelegschaft = new JsonBelegschaft(pfadZurBelegschaft());
        return jsonGestützteBelegschaft.alleMitarbeiter();
    }

    private void angestelltSind(String... nummern) throws IOException {
        final var json = new ObjectMapper().writeValueAsString(nummern);
        Files.writeString(pfadZurBelegschaft(), json);
    }

    private Path pfadZurBelegschaft() {
        return this.tempDir.resolve("test-belegschaft.json");
    }
}
