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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

        this.mvc.perform(put("/api/arbeitsstunden/2024/7/Carol")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
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
        this.mvc.perform(put("/api/arbeitsstunden/2024/7/Carol")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "stunden": 40,
                                  "minuten": 2
                                }"""))
                .andExpect(status().isOk());
    }


    @Test
    @BerechtigungArbeitsstundenErfassen
    void ungültigesJahr() throws Exception {
        final var ungültigesJahr = "bogus";
        this.mvc.perform(put("/api/arbeitsstunden/" + ungültigesJahr + "/7/Carol")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "stunden": 40,
                                  "minuten": 2
                                }"""))
                .andExpect(status().isBadRequest());
    }

    @Test
    @BerechtigungArbeitsstundenErfassen
    void ungültigerMonat() throws Exception {
        final var ungültigerMonat = "27";
        this.mvc.perform(put("/api/arbeitsstunden/2024/" + ungültigerMonat + "/Carol")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "stunden": 40,
                                  "minuten": 2
                                }"""))
                .andExpect(status().isBadRequest());
    }

    @Test
    @BerechtigungArbeitsstundenErfassen
    void negativeStunden() throws Exception {
        this.mvc.perform(put("/api/arbeitsstunden/2024/7/Carol")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "stunden": -1,
                                  "minuten": 2
                                }"""))
                .andExpect(status().isBadRequest());
    }

    @Test
    @BerechtigungArbeitsstundenErfassen
    void fehlendeMinuten() throws Exception {
        this.mvc.perform(put("/api/arbeitsstunden/2024/7/Carol")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "stunden": 1
                                }"""))
                .andExpect(status().isBadRequest());
    }

    @Test
    @BerechtigungArbeitsstundenErfassen
    void falscherMinutenDatentyp() throws Exception {
        this.mvc.perform(put("/api/arbeitsstunden/2024/7/Carol")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "stunden": 1,
                                  "minuten": "ich bin keine minute"
                                }"""))
                .andExpect(status().isBadRequest());
    }

    @Test
    @BerechtigungArbeitsstundenErfassen
    void unbekannterMitarbeiter() throws Exception {
        final var unbekannteMitarbeiterNummer = "JohnDoe";
        aktualisiereMonatsArbeitsstunden.unbekannterMitarbeiter(unbekannteMitarbeiterNummer);
        this.mvc.perform(put("/api/arbeitsstunden/2024/7/" + unbekannteMitarbeiterNummer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "stunden": 1,
                                  "minuten": 2
                                }"""))
                .andExpect(status().isNotFound());
    }
}
