package example.lohnsoftware.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.lohnsoftware.core.Belegschaft;
import example.lohnsoftware.core.Mitarbeiter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JsonBelegschaft implements Belegschaft {

    private final Path pfadZurBelegschaft;

    public JsonBelegschaft(final Path pfadZurBelegschaft) {
        this.pfadZurBelegschaft = pfadZurBelegschaft;
    }

    @Override
    public Set<Mitarbeiter> alleMitarbeiter() {
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