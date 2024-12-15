package example.lohnsoftware.http;

import example.lohnsoftware.core.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArbeitsstundenRestController {

    final AktualisiereMonatsArbeitsstunden aktualisiereMonatsArbeitsstunden;

    public ArbeitsstundenRestController(AktualisiereMonatsArbeitsstunden aktualisiereMonatsArbeitsstunden) {
        this.aktualisiereMonatsArbeitsstunden = aktualisiereMonatsArbeitsstunden;
    }

    @PutMapping(value = "/api/arbeitsstunden/{jahr}/{monat}/{mitarbeiternummer}")
    public ResponseEntity<?> aktualisiereArbeitsstunden(
            @PathVariable int jahr
            , @PathVariable int monat
            , @PathVariable String mitarbeiternummer
            , @Valid @RequestBody AktualisiereArbeitsstundenRequestDTO body
            , BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        final var mitarbeiter = Mitarbeiter.parse(mitarbeiternummer);
        final var localMonth = LocalMonth.parse(jahr, monat);
        final var arbeitsstunden = Arbeitsstunden.parse(body.stunden, body.minuten);

        if (localMonth.isEmpty() || arbeitsstunden.isEmpty() || mitarbeiter.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        final var monatsArbeitsstunden = new MonatsArbeitsstunden(localMonth.get(), mitarbeiter.get(), arbeitsstunden.get());
        final var ergebnis = aktualisiereMonatsArbeitsstunden.aktualisiere(monatsArbeitsstunden);

        if (ergebnis.isRight()) {
            return ResponseEntity.ok().build();
        }

        return switch (ergebnis.getLeft()) {
            case AktualisiereMonatsArbeitsstunden.UnbekannterMitarbeiter ignored -> ResponseEntity.notFound().build();
            case AktualisiereMonatsArbeitsstunden.Fehler ignored -> ResponseEntity.internalServerError().build();
        };

    }
}
