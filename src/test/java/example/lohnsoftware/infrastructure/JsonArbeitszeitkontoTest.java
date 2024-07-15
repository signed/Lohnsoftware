package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Arbeitsstunden;
import example.lohnsoftware.core.LocalMonth;
import example.lohnsoftware.core.Mitarbeiter;
import example.lohnsoftware.core.MonatsArbeitsstunden;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;


class JsonArbeitszeitkontoTest {

    @Test
    void generiereJahrUndMonatsScharfenSpeicherPfadFürMitarbeiterArbeitsstunden() {
        final var einziffernMonat = new MonatsArbeitsstunden(LocalMonth.Erstelle(2024, 7), Mitarbeiter.Erstelle("Alice"), Arbeitsstunden.Erstelle(27, 12));
        assertThat(relativerPfadZumSpeicherort(einziffernMonat))
                .isRelative()
                .isEqualTo(Path.of("2024", "07", "Alice.json"));

        final var zweiZiffernMonat = new MonatsArbeitsstunden(LocalMonth.Erstelle(2024, 11), Mitarbeiter.Erstelle("Alice"), Arbeitsstunden.Erstelle(27, 12));
        assertThat(relativerPfadZumSpeicherort(zweiZiffernMonat))
                .isRelative()
                .isEqualTo(Path.of("2024", "11", "Alice.json"));
    }

    private static Path relativerPfadZumSpeicherort(MonatsArbeitsstunden monatsArbeitsstunden) {
        final var localMonth = monatsArbeitsstunden.month();
        final var jahrOrdner = localMonth.year().toString();
        final var monatsOrdner = String.format(Locale.US, "%02d", localMonth.month().getValue());
        final var dateiName = monatsArbeitsstunden.mitarbeiter().nummer() + ".json";
        return Path.of(jahrOrdner, monatsOrdner, dateiName);
    }
}
