package za.connect.cashdispense.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.connect.cashdispense.domain.CashDispenseResponsePage;
import za.connect.cashdispense.services.CashDispenseService;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("cash/dispense")
public class DispenseRestController {

    final Logger logger = LoggerFactory.getLogger(DispenseRestController.class);

    private CashDispenseService cashDispenseService;

    public DispenseRestController(final CashDispenseService cashDispenseService) {
        this.cashDispenseService = cashDispenseService;
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CashDispenseResponsePage> getBreakdown(@RequestParam("randNote") final String randNote,
                                                                  @RequestParam("amountDue") final String amountDue) {
        logger.info("processing request with randNote {} and amountDue {}", randNote, amountDue);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setAccessControlAllowOrigin("http://localhost:4200");
        CashDispenseResponsePage cashDispenseResponsePage =
        cashDispenseService.getDispensedBreakdown(Double.valueOf(randNote), Double.valueOf(amountDue));

        return new ResponseEntity<CashDispenseResponsePage>(
                cashDispenseResponsePage, responseHeaders, HttpStatus.OK);

    }

}
