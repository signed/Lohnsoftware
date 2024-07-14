package example.lohnsoftware.core;

import java.util.Optional;

public record SchreibeMonatsArbeitsstundenErgebnis(Optional<String> erfolg) {
    public static SchreibeMonatsArbeitsstundenErgebnis erfolg(String nachricht) {
        return new SchreibeMonatsArbeitsstundenErgebnis(Optional.of(nachricht));
    }
}
