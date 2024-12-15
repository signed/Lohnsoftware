package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

class MinutenTest {

    @Test
    void gültigeMinutenWerteLiegenZwischen_0_und_59() {
        assertThat(Minuten.Parse(0)).containsOnRight(new Minuten(0));
        assertThat(Minuten.Parse(59)).containsOnRight(new Minuten(59));
    }

    @Test
    void minutenWerteGrößerGleich_60_SindUngültigeMinuten() {
        assertThat(Minuten.Parse(60)).isLeft();
    }

    @Test
    void minutenWerteKleinerAls_0_SindUngültig() {
        assertThat(Minuten.Parse(-1)).isLeft();
    }
}

