package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import static example.lohnsoftware.core.LocalDateMother.anyDateInSameMonthBefore;
import static example.lohnsoftware.core.LocalDateMother.lastLocalDateOfAnyMonth;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ZeiterfassungImporterTest {

    @Test
    void übertrageDieImAktuellenMonatGearbeitetenStundenInDieLohnverarbeitung() {
        final var heute = lastLocalDateOfAnyMonth();
        final var month = LocalMonth.from(heute);
        final var uhr = new FixeUhr(heute);
        final var lohnsoftware = new LohnsoftwareFake();
        final var zeiterfassung = new ZeiterfassungFake();
        final var belegschaft = new BelegschaftFake();

        final var mitarbeiter = new Mitarbeiter("mitarbeiter eins");

        belegschaft.einstellen(mitarbeiter);
        zeiterfassung.arbeitet(mitarbeiter, anyDateInSameMonthBefore(heute), Arbeitsstunden.Dauer(8, 42));


        final var importer = new ZeiterfassungImporter(zeiterfassung, lohnsoftware, belegschaft, uhr);
        importer.importiereArbeitsstunden();

        assertThat(lohnsoftware.gearbeiteteStunden(mitarbeiter, month)).isEqualTo(Arbeitsstunden.Dauer(8, 42));
    }


}
