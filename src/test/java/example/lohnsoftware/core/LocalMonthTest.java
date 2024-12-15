package example.lohnsoftware.core;

import example.lohnsoftware.lang.Converter;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class LocalMonthTest {

    @Test
    void parseGültigeWerte() {
        final var parse = LocalMonth.Parse(2024, 7);
        final var expected = new LocalMonth(Year.of(2024), Month.JULY);
        assertThat(parse).hasValue(expected);
        assertThat(Converter.eitherFrom(parse)).containsOnRight(expected);
    }

    @Test
    void behandleUngültigeJahre() {
        assertThat(LocalMonth.Parse(Year.MIN_VALUE - 1, 7)).isEmpty();
        assertThat(LocalMonth.Parse(Year.MAX_VALUE + 1, 7)).isEmpty();
    }

    @Test
    void behandleUngültigeMonate() {
        assertThat(LocalMonth.Parse(2024, 0)).isEmpty();
        assertThat(LocalMonth.Parse(2024, 13)).isEmpty();
    }

}
