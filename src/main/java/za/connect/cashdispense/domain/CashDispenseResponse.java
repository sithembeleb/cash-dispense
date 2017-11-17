package za.connect.cashdispense.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashDispenseResponse {

    @JsonProperty(required = true)
    private String cashDenomination;

    @JsonProperty(required = true)
    private int numberOfCashDenomination;
}
