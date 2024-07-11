package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MinutenTest {

    @Test
    void gültigeMinutenWerteLiegenZwischen_0_und_59() {
        assertThat(Minuten.Erstelle(0)).hasValue(new Minuten(0));
        assertThat(Minuten.Erstelle(59)).hasValue(new Minuten(59));
    }

    @Test
    void minutenWerteGrößerGleich_60_SindUngültigeMinuten() {
        assertThat(Minuten.Erstelle(60)).isEmpty();
    }

    @Test
    void minutenWerteKleinerAls_0_SindUngültig() {
        assertThat(Minuten.Erstelle(-1)).isEmpty();
    }
}

