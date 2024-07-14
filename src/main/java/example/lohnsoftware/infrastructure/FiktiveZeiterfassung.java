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

public class FiktiveZeiterfassung implements Zeiterfassung {

    public record StundenMinutenDTO(int stunden, int minuten) {
    }

    final Path pfadZurZeiterfassung = Path.of("daten", "zeiterfassung.json");

    public FiktiveZeiterfassung() {
    }

    @Override
    public Arbeitsstunden arbeitsstundenFür(Mitarbeiter mitarbeiter, LocalMonth month) {
        try {
            final var mapper = new ObjectMapper();
            TypeFactory typeFactory = mapper.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, StundenMinutenDTO.class);
            final var data = pfadZurZeiterfassung.toAbsolutePath().toFile();
            System.out.println(data);

            final HashMap<String, StundenMinutenDTO> daten = mapper.readValue(data, mapType);
            final var alice = daten.get(mitarbeiter.nummer());
            if (alice == null) {
                return Arbeitsstunden.KeineArbeitsstunden();
            }
            return Arbeitsstunden.Erstelle(alice.stunden, alice.minuten);
        } catch (IOException e) {
            // Todo: optional zurückgeben um den fehlerfall erkenn zu können um vorher bereits geschriebene gültige daten nicht durch 0 zu ersetzen
            return Arbeitsstunden.KeineArbeitsstunden();
        }
    }
}
