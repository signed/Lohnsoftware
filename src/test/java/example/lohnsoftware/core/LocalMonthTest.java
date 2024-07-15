package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocalMonthTest {

    @Test
    void parseGültigeWerte() {
        assertThat(LocalMonth.Parse(2024, 7)).hasValue(new LocalMonth(Year.of(2024), Month.JULY));
    }
}
