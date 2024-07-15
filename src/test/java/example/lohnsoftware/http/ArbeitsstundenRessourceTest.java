package example.lohnsoftware.http;

import example.lohnsoftware.core.AktualisiereMonatsArbeitsstundenFake;
import example.lohnsoftware.core.Arbeitsstunden;
import example.lohnsoftware.core.LocalMonth;
import example.lohnsoftware.core.Mitarbeiter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ArbeitsstundenRessource.class)
class ArbeitsstundenRessourceTest {

    @TestConfiguration
    public static class Konfiguration {

        @Bean
        @Primary
        public AktualisiereMonatsArbeitsstundenFake monatsArbeitsstundenFake() {
            return new AktualisiereMonatsArbeitsstundenFake();
        }
    }

    @Autowired
    MockMvc mvc;

    @Autowired
    AktualisiereMonatsArbeitsstundenFake aktualisiereMonatsArbeitsstunden;

    @Test
    @WithMockUser(authorities = {})
    void roleUserKannKeineArbeitsstundenErfassen() throws Exception {

        this.mvc.perform(put("/api/arbeitsstunden/2024/7/Carol").content("""
                        {
                          "stunden": 40,
                          "minuten": 2
                        }"""))
                .andExpect(status().isForbidden());

        final var carol = Mitarbeiter.Erstelle("Carol");
        final var july2024 = LocalMonth.Erstelle(2024, 7);
        assertThat(aktualisiereMonatsArbeitsstunden.gearbeiteteStunden(carol, july2024)).isEqualTo(Arbeitsstunden.Erstelle(40, 2));
    }

    @Test
    @BerechtigungArbeitsstundenErfassen
    void mitarbeiteMitDerBerechtigungZeiterfassungKönnenArbeitsstundenErfassen() throws Exception {
        this.mvc.perform(put("/api/arbeitsstunden/2024/7/Carol").content("""
                        {
                          "stunden": 40,
                          "minuten": 2
                        }"""))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hallo Abrechnung")));

    }

    // Bad Request Szenarien
    // - mitarbeiter unbekannt
    // - stunde 25
    // - stunde IchBinKeineMinute
    // - jahr parsen
    // - monat 43
}
