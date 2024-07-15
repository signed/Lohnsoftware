package example.lohnsoftware.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.lohnsoftware.core.Arbeitsstunden;
import example.lohnsoftware.core.Arbeitszeitkonto;
import example.lohnsoftware.core.MonatsArbeitsstunden;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

public class JsonArbeitszeitkonto implements Arbeitszeitkonto {

    public static Path relativerPfadZumSpeicherort(MonatsArbeitsstunden monatsArbeitsstunden) {
        final var localMonth = monatsArbeitsstunden.month();
        final var jahrOrdner = localMonth.year().toString();
        final var monatsOrdner = String.format(Locale.US, "%02d", localMonth.month().getValue());
        final var dateiName = monatsArbeitsstunden.mitarbeiter().nummer() + ".json";
        return Path.of(jahrOrdner, monatsOrdner, dateiName);
    }

    private record StorageFormat(int stunden, int minuten) {
    }

    private final Path basisPfad;

    public JsonArbeitszeitkonto(Path basisPfad) {
        this.basisPfad = basisPfad;
    }

    @Override
    public Ergebnis erfasse(MonatsArbeitsstunden monatsArbeitsstunden) {
        try {
            var json = """
                    {
                      "stunden": 27,
                      "minuten": 12
                    }""";
            final var arbeitsstunden = monatsArbeitsstunden.arbeitsstunden();
            json = new ObjectMapper().writeValueAsString(new StorageFormat(arbeitsstunden.stunden().wert(), arbeitsstunden.minuten().wert()));
            var datei = basisPfad.resolve(relativerPfadZumSpeicherort(monatsArbeitsstunden));
            Files.createDirectories(datei.getParent());
            Files.writeString(datei, json, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            return Ergebnis.erfolg("Informationen die der Aufrufer im Erfolgsfall braucht");
        } catch (IOException e) {
            e.printStackTrace();
            return Ergebnis.fehlschlag("Informationen die der Aufrufer im Falle eines Fehlschlags braucht");
        }
    }
}
