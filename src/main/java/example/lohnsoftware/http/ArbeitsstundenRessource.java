package example.lohnsoftware.http;

import example.lohnsoftware.core.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArbeitsstundenRessource {

    final AktualisiereMonatsArbeitsstunden aktualisiereMonatsArbeitsstunden;

    public ArbeitsstundenRessource(AktualisiereMonatsArbeitsstunden aktualisiereMonatsArbeitsstunden) {
        this.aktualisiereMonatsArbeitsstunden = aktualisiereMonatsArbeitsstunden;
    }

    @PutMapping(value = "/api/arbeitsstunden/{jahr}/{monat}/{mitarbeiternummer}")
    public String aktualisiereArbeitsstunden() {
        final var carol = Mitarbeiter.Erstelle("Carol");
        final var july2024 = LocalMonth.Erstelle(2024, 7);
        final var arbeitsstunden = Arbeitsstunden.Erstelle(2024, 7);

        aktualisiereMonatsArbeitsstunden.aktualisiere(new MonatsArbeitsstunden(july2024, carol, arbeitsstunden));
        return "Hallo Abrechnung";
    }
}
