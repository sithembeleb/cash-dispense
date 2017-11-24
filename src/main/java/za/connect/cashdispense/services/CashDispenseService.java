package za.connect.cashdispense.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import za.connect.cashdispense.domain.CashDispenseResponsePage;
import za.connect.cashdispense.domain.CashDispenseResponse;

import javax.xml.bind.ValidationException;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Service
public class CashDispenseService {

    final static Double[] validCashDenominations = {100.00, 50.00, 20.00, 10.00, 5.00, 2.00, 1.00, 0.50, 0.20, 0.10, 0.05};
    final static Logger logger = LoggerFactory.getLogger(CashDispenseService.class);

    public CashDispenseResponsePage getDispensedBreakdown(final double randNote, final double amountDue) {
        List<CashDispenseResponse> denominationBreakdown = new ArrayList<>();
        double change = randNote - amountDue;
        double remainder = change;

        logger.info("processing breakdown for {}", change);

        try {
            if (change < 0) {
                throw new ValidationException("Amount due must be less that randNote");
            }

            List<Double> validDenominations = Arrays.asList(validCashDenominations);
            List<Double> filteredList = validDenominations.stream().filter(y -> y.compareTo(change) < 0).collect(Collectors.toList());
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));

            for (Double cashDomination : filteredList) {
                Double numberOfDenominations = remainder / cashDomination;
                if (numberOfDenominations >= 0.00) {
                    denominationBreakdown.add(new CashDispenseResponse(formatter.format(cashDomination), numberOfDenominations.intValue()));
                    remainder = remainder % cashDomination;
                }
            }
        } catch (ValidationException e) {
            return new CashDispenseResponsePage( new ArrayList<>());
        }
        return new CashDispenseResponsePage(denominationBreakdown.stream().filter(y -> y.getNumberOfCashDenomination() > 0).collect(Collectors.toList()));
    }
}
