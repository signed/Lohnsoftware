package example.lohnsoftware.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ZeiterfassungImporterTest {

    @Test
    void übertrageDieImAktuellenMonatGearbeitetenStundenInDieLohnverarbeitung() {
        final var heute = LocalDate.of(2024, Month.AUGUST, 12);
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


    public static LocalDate anyDateInSameMonthBefore(LocalDate date) {
        if (date.getDayOfMonth() == 1) {
            throw new RuntimeException("there is not day before the 1st this month");
        }
        return date.minusDays(1);
    }
}
