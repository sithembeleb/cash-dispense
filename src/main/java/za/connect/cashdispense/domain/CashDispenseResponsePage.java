package za.connect.cashdispense.domain;


import lombok.Data;

import java.util.List;

@Data
public class CashDispenseResponsePage {

    private List<CashDispenseResponse> cashBreakdown;

    private String status;
    private String code;
    private String message;

    public CashDispenseResponsePage(final String code, final String status, final String message,
                                    final List<CashDispenseResponse> cashBreakdown) {
        this.status = status;
        this.code = code;
        this.message  = message;
        this.cashBreakdown = cashBreakdown;
    }
}
