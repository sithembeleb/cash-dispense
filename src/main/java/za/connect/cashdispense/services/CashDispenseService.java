package za.connect.cashdispense.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import za.connect.cashdispense.domain.CashDispenseResponsePage;
import za.connect.cashdispense.domain.CashDispenseResponse;

import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Service
public class CashDispenseService {

    private final static BigDecimal[] validCashDenominations = {new BigDecimal(100.00), new BigDecimal(50.00), new BigDecimal(20.00),
            new BigDecimal(10.00), new BigDecimal(5.00), new BigDecimal(2.00), new BigDecimal(1.00), new BigDecimal(0.50),
            new BigDecimal(0.20), new BigDecimal(0.10), new BigDecimal(0.05)};
    private final static Logger logger = LoggerFactory.getLogger(CashDispenseService.class);

    public CashDispenseResponsePage getDispensedBreakdown(final BigDecimal randNote, final BigDecimal amountDue) {
        List<CashDispenseResponse> denominationBreakdown = new ArrayList<>();
        BigDecimal change = randNote.subtract(amountDue);
        BigDecimal remainder = change;


        List<BigDecimal> validDenominations = Arrays.asList(validCashDenominations);
        List<BigDecimal> filteredList = validDenominations.stream().filter(denomination -> denomination.compareTo(change) == -1).collect(Collectors.toList());
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));

        logger.info("processing breakdown for {}", change);

        try {
            if (change.compareTo(BigDecimal.ZERO) == -1) {
                throw new ValidationException("Amount due must be less that randNote");
            }

            if (!randNote.remainder(BigDecimal.TEN).equals(BigDecimal.ZERO)){
                throw new ValidationException("Please provide a valid note denomination");
            }

            for (BigDecimal cashDomination : filteredList) {
                BigDecimal numberOfDenominations = remainder.compareTo(new BigDecimal("0.50")) == -1 ?
                        remainder.divide(cashDomination, 2, RoundingMode.HALF_UP) : remainder.divide(cashDomination);
                if (numberOfDenominations.intValue() >= 1) {
                    denominationBreakdown.add(new CashDispenseResponse(formatter.format(cashDomination), numberOfDenominations.intValue()));
                    remainder = remainder.remainder(cashDomination);
                }
            }
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new CashDispenseResponsePage(new ArrayList<>(), e.getMessage(), BAD_REQUEST.toString(), change);
        }
        return new CashDispenseResponsePage(denominationBreakdown, null, OK.toString(), change);
    }
}
