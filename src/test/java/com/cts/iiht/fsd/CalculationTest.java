package com.cts.iiht.fsd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cts.iiht.fsd.business.Calculation;

public class CalculationTest {
	@Test
	public void testAdd() {
		int retValue = Calculation.add(15, 25);
		assertEquals(40, retValue);
	}
	
	@Test
	public void testSub() {
		int retValue = Calculation.sub(25, 15);
		assertEquals(10, retValue);
	}
}
