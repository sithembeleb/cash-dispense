package za.connect.cashdispense.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CashDispenseResponsePage {

    @XmlElement(name = "cashBreakdown")
    private List<CashDispenseResponse> cashBreakdown;
    private String status;
    private String message;
    private BigDecimal amountDue;

    public CashDispenseResponsePage(final List<CashDispenseResponse> cashBreakdown, final String message,
                                    final String status, final BigDecimal amountDue) {
        this.cashBreakdown = cashBreakdown;
        this.status = status;
        this.message = message;
        this.amountDue = amountDue;
    }
}
