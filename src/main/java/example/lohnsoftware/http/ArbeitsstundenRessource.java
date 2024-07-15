package example.lohnsoftware.http;

import example.lohnsoftware.core.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArbeitsstundenRessource {

    final AktualisiereMonatsArbeitsstunden aktualisiereMonatsArbeitsstunden;

    public ArbeitsstundenRessource(AktualisiereMonatsArbeitsstunden aktualisiereMonatsArbeitsstunden) {
        this.aktualisiereMonatsArbeitsstunden = aktualisiereMonatsArbeitsstunden;
    }

    @PutMapping(value = "/api/arbeitsstunden/{jahr}/{monat}/{mitarbeiternummer}")
    public String aktualisiereArbeitsstunden(
            @PathVariable int jahr,
            @PathVariable int monat,
            @PathVariable String mitarbeiternummer
    ) {
        final var mitarbeiter = Mitarbeiter.Parse(mitarbeiternummer);
        final var localMonth = LocalMonth.Parse(jahr, monat);
        final var arbeitsstunden = Arbeitsstunden.Parse(40, 2);

        final var monatsArbeitsstunden = new MonatsArbeitsstunden(localMonth.get(), mitarbeiter.get(), arbeitsstunden.get());
        aktualisiereMonatsArbeitsstunden.aktualisiere(monatsArbeitsstunden);
        return "Hallo Abrechnung";
    }
}
