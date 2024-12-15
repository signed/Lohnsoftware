package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.Year;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

class LocalMonthTest {

    @Test
    void parseGültigeWerte() {
        assertThat(LocalMonth.Parse(2024, 7)).containsOnRight(new LocalMonth(Year.of(2024), Month.JULY));
    }

    @Test
    void behandleUngültigeJahre() {
        assertThat(LocalMonth.Parse(Year.MIN_VALUE - 1, 7)).isLeft();
        assertThat(LocalMonth.Parse(Year.MAX_VALUE + 1, 7)).isLeft();
    }

    @Test
    void behandleUngültigeMonate() {
        assertThat(LocalMonth.Parse(2024, 0)).isLeft();
        assertThat(LocalMonth.Parse(2024, 13)).isLeft();
    }

}
