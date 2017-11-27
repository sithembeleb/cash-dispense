package za.connect.cashdispense.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CashDispenseResponsePage {

    @JsonProperty
    private List<CashDispenseResponse> cashBreakdown;
    private String status;
    private String message;

    public CashDispenseResponsePage(final List<CashDispenseResponse> cashBreakdown, final String message, final String status) {
        this.cashBreakdown = cashBreakdown;
        this.status = status;
        this.message = message;
    }
}
