package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static example.lohnsoftware.core.LocalDateMother.anyDateInSameMonthBefore;
import static example.lohnsoftware.core.LocalDateMother.lastLocalDateOfAnyMonth;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ZeiterfassungImporterTest {

    private final LocalDate heute = lastLocalDateOfAnyMonth();
    private final FixeUhr uhr = new FixeUhr(heute);
    private final LocalMonth month = LocalMonth.from(heute);
    private final LohnsoftwareFake lohnsoftware = new LohnsoftwareFake();
    private final ZeiterfassungFake zeiterfassung = new ZeiterfassungFake();
    private final BelegschaftFake belegschaft = new BelegschaftFake();

    @Test
    void übertrageDieImAktuellenMonatGearbeitetenStundenInDieLohnverarbeitung() {
        final var mitarbeiterEins = new Mitarbeiter("mitarbeiter eins");
        final var mitarbeiterZwei = new Mitarbeiter("mitarbeiter zwei");

        belegschaft.einstellen(mitarbeiterEins, mitarbeiterZwei);
        zeiterfassung.arbeitet(mitarbeiterEins, anyDateInSameMonthBefore(heute), Arbeitsstunden.Erstelle(8, 42));
        zeiterfassung.arbeitet(mitarbeiterZwei, anyDateInSameMonthBefore(heute), Arbeitsstunden.Erstelle(4, 9));

        final var importer = new ZeiterfassungImporter(zeiterfassung, lohnsoftware, belegschaft, uhr);
        importer.importiereArbeitsstunden();

        assertThat(lohnsoftware.gearbeiteteStunden(mitarbeiterEins, month)).isEqualTo(Arbeitsstunden.Erstelle(8, 42));
        assertThat(lohnsoftware.gearbeiteteStunden(mitarbeiterZwei, month)).isEqualTo(Arbeitsstunden.Erstelle(4, 9));
    }

}
