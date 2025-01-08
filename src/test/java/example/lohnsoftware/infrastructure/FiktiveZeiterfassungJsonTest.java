package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Arbeitsstunden;
import example.lohnsoftware.core.LocalMonth;
import example.lohnsoftware.core.Mitarbeiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class FiktiveZeiterfassungJsonTest {

    @TempDir
    private Path tempDir;

    @Test
    void leseArbeitszeitFürMitarbeiterDerBereitsArbeitszeitErfasstHat() throws IOException {
        final var alice = Mitarbeiter.erstelle("Alice");
        erfasseArbeitszeitFür(alice, 3, 7);

        final var arbeitsstunden = arbeitsstundenFür(alice).orElseThrow();
        assertThat(arbeitsstunden.stunden().wert()).isEqualTo(3);
        assertThat(arbeitsstunden.minuten().wert()).isEqualTo(7);
    }

    @Test
    void gibKeineArbeitsstundenZurückFürMitarbeiterDieNochKeineArbeitszeitErfasstHaben() throws IOException {
        nochKeineArbeitszeitErfasst();
        final var arbeitsstunden = arbeitsstundenFür(Mitarbeiter.erstelle("NochKeineZeitErfasst")).orElseThrow();

        assertThat(arbeitsstunden.stunden().wert()).isEqualTo(0);
        assertThat(arbeitsstunden.minuten().wert()).isEqualTo(0);
    }

    private Optional<Arbeitsstunden> arbeitsstundenFür(Mitarbeiter mitarbeiter) {
        final LocalMonth notAccessed = null;
        return new FiktiveZeiterfassung(pfadZurZeiterfassung()).arbeitsstundenFür(mitarbeiter, notAccessed);
    }

    private void nochKeineArbeitszeitErfasst() throws IOException {
        Files.writeString(pfadZurZeiterfassung(), "{}");
    }

    private void erfasseArbeitszeitFür(Mitarbeiter mitarbeiter, int stunden, int minuten) throws IOException {
        final var identifikator = mitarbeiter.personalNummer().wert();
        final var jsonTemplate = """
                '{'
                  "{0}": '{'
                    "stunden": {1},
                    "minuten": {2}
                  '}'
                '}'
                """;
        Files.writeString(pfadZurZeiterfassung(), MessageFormat.format(jsonTemplate, identifikator, stunden, minuten));
    }

    private Path pfadZurZeiterfassung() {
        return tempDir.resolve("test-zeiterfassung.json");
    }

}
