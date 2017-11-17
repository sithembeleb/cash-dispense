package za.connect.cashdispense.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.connect.cashdispense.domain.CashDispenseResponse;
import za.connect.cashdispense.domain.CashDispenseResponsePage;
import za.connect.cashdispense.services.CashDispenseService;

import javax.xml.bind.ValidationException;
import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("cash/dispense")
public class DispenseRestController {

    final Logger logger = LoggerFactory.getLogger(DispenseRestController.class);

    private CashDispenseService cashDispenseService;

    public DispenseRestController(final CashDispenseService cashDispenseService) {
        this.cashDispenseService = cashDispenseService;
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public CashDispenseResponsePage getBreakdown(@RequestParam("randNote") final String randNote,
                                                 @RequestParam("amountDue") final String amountDue){
        logger.info(format("processing request with randNote %s and amountDue %s", randNote, amountDue));
        return cashDispenseService.getDispensedBreakdown(Double.valueOf(randNote), Double.valueOf(amountDue));
    }

}
