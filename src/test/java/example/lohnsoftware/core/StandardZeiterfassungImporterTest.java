package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static example.lohnsoftware.core.LocalDateMother.irgendeinLocalDateImSelbenMonatVor;
import static example.lohnsoftware.core.LocalDateMother.letztesLocalDateInIrgendeinemMonat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StandardZeiterfassungImporterTest {

    private final LocalDate heute = letztesLocalDateInIrgendeinemMonat();
    private final FixeUhr uhr = new FixeUhr(heute);
    private final LocalMonth month = LocalMonth.from(heute);
    private final AktualisiereMonatsArbeitsstundenFake lohnsoftware = new AktualisiereMonatsArbeitsstundenFake();
    private final ZeiterfassungFake zeiterfassung = new ZeiterfassungFake();
    private final BelegschaftFake belegschaft = new BelegschaftFake();

    @Test
    void übertrageDieImAktuellenMonatGearbeitetenStundenInDieLohnverarbeitung() {
        final var mitarbeiterEins = Mitarbeiter.erstelle("mitarbeiter eins");
        final var mitarbeiterZwei = Mitarbeiter.erstelle("mitarbeiter zwei");

        belegschaft.einstellen(mitarbeiterEins, mitarbeiterZwei);
        zeiterfassung.arbeitet(mitarbeiterEins, irgendeinLocalDateImSelbenMonatVor(heute), Arbeitsstunden.erstelle(8, 42));
        zeiterfassung.arbeitet(mitarbeiterZwei, irgendeinLocalDateImSelbenMonatVor(heute), Arbeitsstunden.erstelle(4, 9));

        final var importer = new StandardZeiterfassungImporter(zeiterfassung, lohnsoftware, belegschaft, uhr);
        importer.importiereArbeitsstunden();

        assertThat(lohnsoftware.gearbeiteteStunden(mitarbeiterEins, month)).isEqualTo(Arbeitsstunden.erstelle(8, 42));
        assertThat(lohnsoftware.gearbeiteteStunden(mitarbeiterZwei, month)).isEqualTo(Arbeitsstunden.erstelle(4, 9));
    }

}
