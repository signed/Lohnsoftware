package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Arbeitsstunden;
import example.lohnsoftware.core.LocalMonth;
import example.lohnsoftware.core.Mitarbeiter;
import example.lohnsoftware.core.Zeiterfassung;
import example.lohnsoftware.lang.Converter;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.type.MapType;
import tools.jackson.databind.type.TypeFactory;

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
            final var mapper = new JsonMapper();
            TypeFactory typeFactory = mapper.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, StundenMinutenDTO.class);
            final var data = pfadZurZeiterfassung.toAbsolutePath().toFile();

            final HashMap<String, StundenMinutenDTO> daten = mapper.readValue(data, mapType);
            final var erfassteArbeitsstunden = daten.get(mitarbeiter.personalNummer().wert());
            if (erfassteArbeitsstunden == null) {
                return Optional.of(Arbeitsstunden.KeineArbeitsstunden());
            }
            final var arbeitsstunden = Arbeitsstunden.parse(erfassteArbeitsstunden.stunden, erfassteArbeitsstunden.minuten);
            return Converter.optionalFrom(arbeitsstunden);
        } catch (JacksonException e) {
            return Optional.empty();
        }
    }
}
