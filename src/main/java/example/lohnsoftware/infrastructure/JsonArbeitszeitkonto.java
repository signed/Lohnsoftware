package example.lohnsoftware.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.lohnsoftware.core.Arbeitszeitkonto;
import example.lohnsoftware.core.MonatsArbeitsstunden;
import io.vavr.control.Either;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

import static java.nio.file.StandardOpenOption.*;

/**
 * <p>
 * Bei einem JpaArbeitszeitkonto
 * - Umsetzung mit <a href="https://spring.io/projects/spring-data">spring-data</a>
 * - Konflikt Erkennung/Vermeidung <a href="https://docs.spring.io/spring-data/jpa/reference/jpa/locking.html">über Locking</a>
 * </p>
 * <p>
 * Bei einem DynamoDbArbeitszeitkonto
 * - Umsetzung mit <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-dynamodb.html">dynamodb-enhanced</a>
 * - Konflikt Erkennung/Vermeidung <a href="https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.ConditionExpressions.html#Expressions.ConditionExpressions.SimpleComparisons">Condition expressions</a>
 * </p>
 * <p>
 * Die idee ist in beiden Fällen ähnlich. Zusätzlich zu den Nutzdaten wird noch einer Versionsnummer oder Hash der Nutzdaten geschrieben.
 * Nur wenn sich die Daten seit meinem letzten Lesen nicht geändert haben (erkennbar an der Versionsnummer oder Nutzdatenhash), wird das Update ausgeführt.
 * </p>
 * <p>
 * Oder <a href="https://docs.spring.io/spring-integration/reference/distributed-locks.html">Distributed Locks mit Spring Integration</a>.
 * <a href="https://www.youtube.com/watch?v=firwCHbC7-c&t=327s">Spring Tips: Distributed Locks with Spring Integration</a>
 * </p>
 */
public class JsonArbeitszeitkonto implements Arbeitszeitkonto {

    private final ObjectMapper objectMapper;

    public static Path relativerPfadZumSpeicherort(MonatsArbeitsstunden monatsArbeitsstunden) {
        final var localMonth = monatsArbeitsstunden.month();
        final var jahrOrdner = localMonth.year().toString();
        final var monatsOrdner = String.format(Locale.US, "%02d", localMonth.month().getValue());
        final var dateiName = monatsArbeitsstunden.mitarbeiter().personalNummer().wert() + ".json";
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
    public Either<Fehler,String> erfasse(MonatsArbeitsstunden monatsArbeitsstunden) {
        try {
            final var json = erstelleJsonFür(monatsArbeitsstunden);
            var datei = pfadZurDatei(monatsArbeitsstunden);
            return lockeDateiUndSchreibe(datei, json);
        } catch (IOException e) {
            return Fehler.fehlschlag("Informationen die der Aufrufer im Falle eines Fehlschlags braucht");
        }
    }

    private static Either<Fehler,String> lockeDateiUndSchreibe(Path datei, String json) {
        try {
            Files.createDirectories(datei.getParent());
            try (FileChannel channel = FileChannel.open(datei, WRITE, CREATE, TRUNCATE_EXISTING)) {
                try (final var ignored = channel.lock()) {
                    channel.truncate(0);
                    final var bufferToWrite = ByteBuffer.wrap(json.getBytes(StandardCharsets.UTF_8));
                    channel.write(bufferToWrite);
                }
            }
            return Arbeitszeitkonto.erfolg("Informationen die der Aufrufer im Erfolgsfall braucht");
        } catch (IOException | RuntimeException e) {
            return Fehler.fehlschlag("Schreibe ist fehlgeschlagen");
        }
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
