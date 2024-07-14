package example.lohnsoftware.infrastructure;

import example.lohnsoftware.core.MonatsArbeitsstunden;
import example.lohnsoftware.core.MonatsArbeitsstundenSchreiber;

public class JsonGestützterMonatsArbeitsstundenSchreiber implements MonatsArbeitsstundenSchreiber {
    @Override
    public Ergebnis schreibe(MonatsArbeitsstunden monatsArbeitsstunden) {
        return Ergebnis.ergebnis("");
    }
}
