package example.lohnsoftware.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.lohnsoftware.core.Arbeitszeitkonto;
import example.lohnsoftware.core.MonatsArbeitsstunden;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

public class JsonArbeitszeitkonto implements Arbeitszeitkonto {

    private final ObjectMapper objectMapper;

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
        objectMapper = new ObjectMapper();
    }

    @Override
    public Ergebnis erfasse(MonatsArbeitsstunden monatsArbeitsstunden) {
        try {
            final var json = erstelleJsonFür(monatsArbeitsstunden);
            var datei = pfadZurDatei(monatsArbeitsstunden);
            return lockeDateiUndSchreibe(datei, json);
        } catch (IOException e) {
            e.printStackTrace();
            return Ergebnis.fehlschlag("Informationen die der Aufrufer im Falle eines Fehlschlags braucht");
        }
    }

    private static Ergebnis lockeDateiUndSchreibe(Path datei, String json) throws IOException {
        Files.createDirectories(datei.getParent());
        Files.writeString(datei, json, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        return Ergebnis.erfolg("Informationen die der Aufrufer im Erfolgsfall braucht");
    }

    private Path pfadZurDatei(MonatsArbeitsstunden monatsArbeitsstunden) {
        return basisPfad.resolve(relativerPfadZumSpeicherort(monatsArbeitsstunden));
    }

    private String erstelleJsonFür(MonatsArbeitsstunden monatsArbeitsstunden) throws JsonProcessingException {
        final var arbeitsstunden = monatsArbeitsstunden.arbeitsstunden();
        final var stunden = arbeitsstunden.stunden().wert();
        final var minuten = arbeitsstunden.minuten().wert();
        return objectMapper.writeValueAsString(new StorageFormat(stunden, minuten));
    }
}
