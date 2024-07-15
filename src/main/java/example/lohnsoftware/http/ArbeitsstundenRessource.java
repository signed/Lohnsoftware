package example.lohnsoftware.http;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArbeitsstundenRessource {

    @PutMapping(value = "/api/arbeitsstunden/{jahr}/{monat}/{mitarbeiternummer}")
    public String aktualisiereArbeitsstunden() {
        return "Hallo Abrechnung";
    }
}
