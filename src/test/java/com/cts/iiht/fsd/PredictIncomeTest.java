package com.cts.iiht.fsd;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.cts.iiht.fsd.business.PredictIncome;
import com.cts.iiht.fsd.model.PredictionInput;
import com.cts.iiht.fsd.model.Report;

public class PredictIncomeTest {

	@Test
	public void testPredictIncome() {
		PredictIncome predictIncome = new PredictIncome();
		PredictionInput input = new PredictionInput();
		createInputData(input);
		Map<String, List<? extends Report>> map = predictIncome.incomeReport(input);
		assertEquals(map.size(), 3);

	}

	private void createInputData(PredictionInput input) {
		input.setStartingSalary(new BigDecimal(10000));
		input.setIncrementFrequency(2);
		input.setDeductionFrequency(1);
		input.setIncrementPercent(3);
		input.setDeductionInIncome(2);
	}

}
