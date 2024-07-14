package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Arbeitsstunden;
import example.lohnsoftware.core.LocalMonth;
import example.lohnsoftware.core.Mitarbeiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class FiktiveZeiterfassungJsonTest {

    @TempDir
    private Path tempDir;

    @Test
    void leseArbeitszeitFürMitarbeiterDerBereitsArbeitszeitErfasstHat() throws IOException {

        final var arbeitsstunden = arbeitsstundenFür(new Mitarbeiter("Alice"));

        assertThat(arbeitsstunden.stunden().wert()).isEqualTo(3);
        assertThat(arbeitsstunden.minuten().wert()).isEqualTo(7);
    }

    @Test
    void gipKeineArbeitsstundenZurückFürMitarbeiterDieNochKeineArbeitszeitErfasstHaben() throws IOException {
        final var arbeitsstunden = arbeitsstundenFür(new Mitarbeiter("NochKeineZeitErfasst"));

        assertThat(arbeitsstunden.stunden().wert()).isEqualTo(0);
        assertThat(arbeitsstunden.minuten().wert()).isEqualTo(0);
    }

    private Arbeitsstunden arbeitsstundenFür(Mitarbeiter alice) throws IOException {
        final var pfadZurZeiterfassung = tempDir.resolve("test-zeiterfassung.json");
        Files.writeString(pfadZurZeiterfassung, """
                {
                  "Alice": {
                    "stunden": 3,
                    "minuten": 7
                  }
                }
                """);
        final LocalMonth notAccessed = null;

        return new FiktiveZeiterfassung(pfadZurZeiterfassung).arbeitsstundenFür(alice, notAccessed);
    }

}
