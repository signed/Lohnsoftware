package example.lohnsoftware.cron;

import example.lohnsoftware.core.ZeiterfassungImporter;

public class ArbeitsstundenImporteZähler implements ZeiterfassungImporter {
    public int anzahlImporte = 0;

    @Override
    public void importiereArbeitsstunden() {
        this.anzahlImporte++;
    }
}
