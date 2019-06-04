package za.connect.cashdispense.web.rest;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.connect.cashdispense.configuration.CashDispenseConfigurationProperties;
import za.connect.cashdispense.domain.CashDispenseResponsePage;
import za.connect.cashdispense.services.CashDispenseService;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("cash/dispense")
@Api(tags = { "cash dispense" })
public class DispenseRestController {

    final Logger logger = LoggerFactory.getLogger(DispenseRestController.class);

    private CashDispenseService cashDispenseService;

    @Autowired
    private CashDispenseConfigurationProperties cashDispenseConfigurationProperties;

    public DispenseRestController(final CashDispenseService cashDispenseService) {
        this.cashDispenseService = cashDispenseService;
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CashDispenseResponsePage> getBreakdown(@RequestParam("randNote") final String randNote,
                                                                   @RequestParam("amountDue") final String amountDue) {
        logger.info("processing request with randNote {} and amountDue {}", randNote, amountDue);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setAccessControlAllowOrigin(cashDispenseConfigurationProperties.getAllowedOrigin());
        CashDispenseResponsePage cashDispenseResponsePage =
        cashDispenseService.getDispensedBreakdown(new BigDecimal(randNote), new BigDecimal(amountDue));

        return new ResponseEntity<CashDispenseResponsePage>(
                cashDispenseResponsePage, responseHeaders, HttpStatus.OK);

    }

}
