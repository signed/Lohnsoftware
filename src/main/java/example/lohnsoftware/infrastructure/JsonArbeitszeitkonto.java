package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.MonatsArbeitsstunden;
import example.lohnsoftware.core.Arbeitszeitkonto;

public class JsonArbeitszeitkonto implements Arbeitszeitkonto {
    @Override
    public Ergebnis erfasse(MonatsArbeitsstunden monatsArbeitsstunden) {
        return Ergebnis.ergebnis("");
    }
}
