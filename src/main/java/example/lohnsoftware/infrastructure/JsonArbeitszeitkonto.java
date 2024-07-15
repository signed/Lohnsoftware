package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Arbeitszeitkonto;
import example.lohnsoftware.core.MonatsArbeitsstunden;

import java.nio.file.Path;
import java.util.Locale;

public class JsonArbeitszeitkonto implements Arbeitszeitkonto {
    public static Path relativerPfadZumSpeicherort(MonatsArbeitsstunden monatsArbeitsstunden) {
        final var localMonth = monatsArbeitsstunden.month();
        final var jahrOrdner = localMonth.year().toString();
        final var monatsOrdner = String.format(Locale.US, "%02d", localMonth.month().getValue());
        final var dateiName = monatsArbeitsstunden.mitarbeiter().nummer() + ".json";
        return Path.of(jahrOrdner, monatsOrdner, dateiName);
    }

    @Override
    public Ergebnis erfasse(MonatsArbeitsstunden monatsArbeitsstunden) {
        return Ergebnis.ergebnis("");
    }
}
