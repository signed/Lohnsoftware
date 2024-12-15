package example.lohnsoftware.lang;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static example.lohnsoftware.lang.Converter.eitherFrom;
import static example.lohnsoftware.lang.Converter.optionalFrom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class ConverterTest {

    @Test
    void nullRoundTrip() {
        final var either = eitherFrom(Optional.empty());
        final var optional = optionalFrom(either);
        assertThat(optional).isEmpty();
    }

    @Test
    void valueRoundTrip() {
        final var either = eitherFrom(Optional.of(7));
        final var optional = optionalFrom(either);
        assertThat(optional).hasValue(7);
    }

    @Test
    void leftRoundTrip() {
        final var optional = optionalFrom(Either.<Integer, String>left(5));
        final var either = eitherFrom(optional);
        assertThat(either).containsOnLeft(null);
    }

    @Test
    void rightRoundTrip() {
        final var optional = optionalFrom(Either.<Integer, String>right("value"));
        final var either = eitherFrom(optional);
        assertThat(either).containsOnRight("value");
    }
}
