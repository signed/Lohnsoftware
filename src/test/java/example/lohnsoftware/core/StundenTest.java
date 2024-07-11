package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StundenTest {

    @Test
    void stundenWerteGrößerAls_0_sindGültig() {
        assertThat(Stunden.Erstelle(0)).hasValue(new Stunden(0));
        assertThat(Stunden.Erstelle(42)).hasValue(new Stunden(42));
    }

    @Test
    void negativeStundenSindUngültig() {
        assertThat(Stunden.Erstelle(-1)).isEmpty();
    }
}
