package example.lohnsoftware.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.lohnsoftware.core.Arbeitsstunden;
import example.lohnsoftware.core.LocalMonth;
import example.lohnsoftware.core.Mitarbeiter;
import example.lohnsoftware.core.MonatsArbeitsstunden;
import org.assertj.core.api.AssertProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContentAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static example.lohnsoftware.infrastructure.JsonArbeitszeitkonto.relativerPfadZumSpeicherort;
import static org.assertj.core.api.Assertions.assertThat;


class JsonArbeitszeitkontoTest {

    @TempDir
    Path tempDir;


    @BeforeEach
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    void schreibeArbeitsstundenInDatei() throws IOException {
        final var monatsArbeitsstunden = new MonatsArbeitsstunden(LocalMonth.erstelle(2024, 7), Mitarbeiter.erstelle("Alice"), Arbeitsstunden.erstelle(27, 12));
        new JsonArbeitszeitkonto(tempDir).erfasse(monatsArbeitsstunden);
        assertThat(geschriebenesJson(monatsArbeitsstunden)).isEqualToJson("""
                {
                  "stunden": 27,
                  "minuten": 12
                }""");

    }

    private AssertProvider<JsonContentAssert> geschriebenesJson(MonatsArbeitsstunden monatsArbeitsstunden) throws IOException {
        return forJson(geschriebenerDateiinhalt(monatsArbeitsstunden));
    }

    @Test
    void überschreibeBereitsExistierendeDatei() throws IOException {
        final var initial = new MonatsArbeitsstunden(LocalMonth.erstelle(2024, 7), Mitarbeiter.erstelle("Alice"), Arbeitsstunden.erstelle(27, 12));
        final var konto = new JsonArbeitszeitkonto(tempDir);
        konto.erfasse(initial);
        final var aktualisierung = new MonatsArbeitsstunden(LocalMonth.erstelle(2024, 7), Mitarbeiter.erstelle("Alice"), Arbeitsstunden.erstelle(30, 45));
        konto.erfasse(aktualisierung);
        assertThat(geschriebenesJson(aktualisierung)).isEqualTo("""
                {
                  "stunden": 30,
                  "minuten": 45
                }""");

    }

    @Test
    void generiereJahrUndMonatsScharfenSpeicherPfadFürMitarbeiterArbeitsstunden() {
        final var einziffernMonat = new MonatsArbeitsstunden(LocalMonth.erstelle(2024, 7), Mitarbeiter.erstelle("Alice"), Arbeitsstunden.erstelle(27, 12));
        assertThat(relativerPfadZumSpeicherort(einziffernMonat))
                .isRelative()
                .isEqualTo(Path.of("2024", "07", "Alice.json"));

        final var zweiZiffernMonat = new MonatsArbeitsstunden(LocalMonth.erstelle(2024, 11), Mitarbeiter.erstelle("Alice"), Arbeitsstunden.erstelle(27, 12));
        assertThat(relativerPfadZumSpeicherort(zweiZiffernMonat))
                .isRelative()
                .isEqualTo(Path.of("2024", "11", "Alice.json"));
    }

    private String geschriebenerDateiinhalt(MonatsArbeitsstunden monatsArbeitsstunden) throws IOException {
        return Files.readString(
                tempDir.resolve(relativerPfadZumSpeicherort(monatsArbeitsstunden))
        );
    }

    // bin mir nicht sicher, ob das die richtige Art ist JsonContentAssert zu verwenden, inspiration kommt von
    // https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot-test/src/test/java/org/springframework/boot/test/json/JsonContentAssertTests.java
    private AssertProvider<JsonContentAssert> forJson(String json) {
        return () -> new JsonContentAssert(JsonArbeitszeitkontoTest.class, json);
    }

}
