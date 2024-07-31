package example.lohnsoftware.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import example.lohnsoftware.core.Arbeitsstunden;
import example.lohnsoftware.core.LocalMonth;
import example.lohnsoftware.core.Mitarbeiter;
import example.lohnsoftware.core.Zeiterfassung;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Optional;

public class FiktiveZeiterfassung implements Zeiterfassung {

    public record StundenMinutenDTO(int stunden, int minuten) {
    }

    private final Path pfadZurZeiterfassung;


    public FiktiveZeiterfassung(final Path pfadZurZeiterfassung) {
        this.pfadZurZeiterfassung = pfadZurZeiterfassung;
    }

    @Override
    public Optional<Arbeitsstunden> arbeitsstundenFür(Mitarbeiter mitarbeiter, LocalMonth month) {
        try {
            final var mapper = new ObjectMapper();
            TypeFactory typeFactory = mapper.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, StundenMinutenDTO.class);
            final var data = pfadZurZeiterfassung.toAbsolutePath().toFile();

            final HashMap<String, StundenMinutenDTO> daten = mapper.readValue(data, mapType);
            final var erfassteArbeitsstunden = daten.get(mitarbeiter.personalNummer().wert());
            if (erfassteArbeitsstunden == null) {
                return Optional.of(Arbeitsstunden.KeineArbeitsstunden());
            }
            return Arbeitsstunden.Parse(erfassteArbeitsstunden.stunden, erfassteArbeitsstunden.minuten);
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
