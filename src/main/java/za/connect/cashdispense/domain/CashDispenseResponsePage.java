package za.connect.cashdispense.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CashDispenseResponsePage {

    @JsonProperty
    private List<CashDispenseResponse> cashBreakdown;

    public CashDispenseResponsePage(List<CashDispenseResponse> cashBreakdown) {
        this.cashBreakdown = cashBreakdown;
    }
}
