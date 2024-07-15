package example.lohnsoftware.http;

import example.lohnsoftware.core.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArbeitsstundenRessource {

    final AktualisiereMonatsArbeitsstunden aktualisiereMonatsArbeitsstunden;

    public ArbeitsstundenRessource(AktualisiereMonatsArbeitsstunden aktualisiereMonatsArbeitsstunden) {
        this.aktualisiereMonatsArbeitsstunden = aktualisiereMonatsArbeitsstunden;
    }

    @PutMapping(value = "/api/arbeitsstunden/{jahr}/{monat}/{mitarbeiternummer}")
    public ResponseEntity<?> aktualisiereArbeitsstunden(
            @PathVariable int jahr,
            @PathVariable int monat,
            @PathVariable String mitarbeiternummer,
            @Valid @RequestBody AktualisiereArbeitsstundenRequestDTO body
    ) {
        final var mitarbeiter = Mitarbeiter.Parse(mitarbeiternummer);
        final var localMonth = LocalMonth.Parse(jahr, monat);
        final var arbeitsstunden = Arbeitsstunden.Parse(body.stunden, body.minuten);

        if (localMonth.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }


        final var monatsArbeitsstunden = new MonatsArbeitsstunden(localMonth.get(), mitarbeiter.get(), arbeitsstunden.get());
        aktualisiereMonatsArbeitsstunden.aktualisiere(monatsArbeitsstunden);
        return ResponseEntity.ok().build();
    }


}
