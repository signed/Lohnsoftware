package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.Arbeitszeitkonto;
import example.lohnsoftware.core.MonatsArbeitsstunden;

public class JsonArbeitszeitkonto implements Arbeitszeitkonto {
    @Override
    public Ergebnis erfasse(MonatsArbeitsstunden monatsArbeitsstunden) {
        return Ergebnis.ergebnis("");
    }
}
