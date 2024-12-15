package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

class MinutenTest {

    @Test
    void gültigeMinutenWerteLiegenZwischen_0_und_59() {
        assertThat(Minuten.parse(0)).containsOnRight(new Minuten(0));
        assertThat(Minuten.parse(59)).containsOnRight(new Minuten(59));
    }

    @Test
    void minutenWerteGrößerGleich_60_SindUngültigeMinuten() {
        assertThat(Minuten.parse(60)).isLeft();
    }

    @Test
    void minutenWerteKleinerAls_0_SindUngültig() {
        assertThat(Minuten.parse(-1)).isLeft();
    }
}

