package example.lohnsoftware.http;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ArbeitsstundenRessource.class)
class ArbeitsstundenRessourceTest {

    @Autowired
    MockMvc mvc;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void allowAdminsToAccessTheResource() throws Exception {
        this.mvc.perform(put("/api/arbeitsstunden/2024/7/Carol").content("""
                        {
                          "stunden": 40,
                          "minuten": 2
                        }"""))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hallo Abrechnung")));

    }

}
