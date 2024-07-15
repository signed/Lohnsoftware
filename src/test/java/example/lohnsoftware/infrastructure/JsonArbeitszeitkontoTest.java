package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Arbeitsstunden;
import example.lohnsoftware.core.LocalMonth;
import example.lohnsoftware.core.Mitarbeiter;
import example.lohnsoftware.core.MonatsArbeitsstunden;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;


class JsonArbeitszeitkontoTest {

    @Test
    void generiereJahrUndMonatsScharfenSpeicherPfadFürMitarbeiterArbeitsstunden() {
        final var einziffernMonat = new MonatsArbeitsstunden(LocalMonth.Erstelle(2024, 7), Mitarbeiter.Erstelle("Alice"), Arbeitsstunden.Erstelle(27, 12));
        assertThat(JsonArbeitszeitkonto.relativerPfadZumSpeicherort(einziffernMonat))
                .isRelative()
                .isEqualTo(Path.of("2024", "07", "Alice.json"));

        final var zweiZiffernMonat = new MonatsArbeitsstunden(LocalMonth.Erstelle(2024, 11), Mitarbeiter.Erstelle("Alice"), Arbeitsstunden.Erstelle(27, 12));
        assertThat(JsonArbeitszeitkonto.relativerPfadZumSpeicherort(zweiZiffernMonat))
                .isRelative()
                .isEqualTo(Path.of("2024", "11", "Alice.json"));
    }

}
