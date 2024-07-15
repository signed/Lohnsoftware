package example.lohnsoftware.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class AktualisiereArbeitsstundenRequestDTOTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void parseStundenUndMinutenInformationen() throws JsonProcessingException {
        var json = """
                {
                  "stunden": 40,
                  "minuten": 2
                }""";
        final var dto = objectMapper.readValue(json, AktualisiereArbeitsstundenRequestDTO.class);

        assertThat(validationErrorMessagesFor(dto)).isEmpty();

        assertThat(dto.stunden).isEqualTo(40);
        assertThat(dto.minuten).isEqualTo(2);
    }

    @Test
    void missingMinuten() throws JsonProcessingException {
        var json = """
                {
                  "stunden": 40
                }""";
        final var dto = objectMapper.readValue(json, AktualisiereArbeitsstundenRequestDTO.class);

        assertThat(validationErrorMessagesFor(dto)).contains("must not be null");
    }

    @Test
    void missingStunden() throws JsonProcessingException {
        var json = """
                {
                  "minuten": 40
                }""";
        final var dto = objectMapper.readValue(json, AktualisiereArbeitsstundenRequestDTO.class);

        assertThat(validationErrorMessagesFor(dto)).contains("must not be null");
    }


    private List<String> validationErrorMessagesFor(AktualisiereArbeitsstundenRequestDTO dto) {
        final Set<ConstraintViolation<AktualisiereArbeitsstundenRequestDTO>> result = validator().validate(dto);
        return result.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }

    private Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}
