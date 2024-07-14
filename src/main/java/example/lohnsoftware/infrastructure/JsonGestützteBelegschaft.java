package example.lohnsoftware.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import example.lohnsoftware.core.Arbeitsstunden;
import example.lohnsoftware.core.Belegschaft;
import example.lohnsoftware.core.Mitarbeiter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class JsonGestützteBelegschaft implements Belegschaft {

    @Override
    public Set<Mitarbeiter> alleMitarbeiter() {
        final var pfadZurBelegschaft = Path.of("daten", "belegschaft.json");
        try {
            final var mapper = new ObjectMapper();
            List<String> mitarbeiterDTOS = mapper.readValue(pfadZurBelegschaft.toFile(), new TypeReference<>() {
            });
            return mitarbeiterDTOS.stream().map(Mitarbeiter::new).collect(Collectors.toSet());
        } catch (IOException e) {
            return Collections.emptySet();
        }
    }
}
