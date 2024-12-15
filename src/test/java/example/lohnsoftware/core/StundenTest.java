package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

class StundenTest {

    @Test
    void stundenWerteGrößerAls_0_sindGültig() {
        assertThat(Stunden.parse(0)).containsOnRight(new Stunden(0));
        assertThat(Stunden.parse(42)).containsOnRight(new Stunden(42));
    }

    @Test
    void negativeStundenSindUngültig() {
        assertThat(Stunden.parse(-1)).isLeft();
    }
}
