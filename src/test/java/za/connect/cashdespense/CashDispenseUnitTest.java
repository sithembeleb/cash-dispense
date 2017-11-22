package za.connect.cashdespense;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import za.connect.cashdispense.domain.CashDispenseResponse;
import za.connect.cashdispense.services.CashDispenseService;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class CashDispenseUnitTest {

	@Autowired
	private CashDispenseService coinDispenseService;

	@Before
	public void setUp() throws Exception {
		coinDispenseService = new CashDispenseService();
	}

	@Test
	public void when_valid_inputs_expect_valid_breakdown() throws Exception{
		List<CashDispenseResponse> denominationBreakdown = coinDispenseService.getDispensedBreakdown(50.00, 25.50).getCashBreakdown();
		assertTrue("R20 must be * 1 ", denominationBreakdown.contains(new CashDispenseResponse("R 20.00", 1)));
		assertTrue("R2 must be * 2 ", denominationBreakdown.contains(new CashDispenseResponse("R 2.00", 2)));
		assertTrue("R0.50 must be * 1 ", denominationBreakdown.contains(new CashDispenseResponse("R 0.50", 1)));
		assertTrue("size should be 3", denominationBreakdown.size() == 3);
	}

	@Test
	public void when_invalid_inputs_expect_no_breakdown() throws Exception {
		List<CashDispenseResponse> denominationBreakdown = coinDispenseService.getDispensedBreakdown(50.85, 100.00).getCashBreakdown();
		assertTrue("No breakdown expected", denominationBreakdown.isEmpty());
		System.out.println(denominationBreakdown.toString());
	}

}
