package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StundenTest {

    @Test
    void stundenWerteGrößerAls_0_sindGültig() {
        assertThat(Stunden.Parse(0)).hasValue(new Stunden(0));
        assertThat(Stunden.Parse(42)).hasValue(new Stunden(42));
    }

    @Test
    void negativeStundenSindUngültig() {
        assertThat(Stunden.Parse(-1)).isEmpty();
    }
}
