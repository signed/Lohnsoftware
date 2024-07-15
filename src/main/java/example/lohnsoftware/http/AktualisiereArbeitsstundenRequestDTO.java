package example.lohnsoftware.http;

import jakarta.validation.constraints.NotNull;

public class AktualisiereArbeitsstundenRequestDTO {
    @NotNull
    public Integer stunden;
    @NotNull
    public Integer minuten;
}
