package com.cts.iiht.fsd.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cts.iiht.fsd.model.DeductionReport;
import com.cts.iiht.fsd.model.IncrementReport;
import com.cts.iiht.fsd.model.PredictionInput;
import com.cts.iiht.fsd.model.PredictionReport;
import com.cts.iiht.fsd.model.Report;

/**
 * Predict the income over a period of time. This would generate incomereport,
 * deduction report and prediction report also.
 * 
 * @author arunds
 */
public class PredictIncome {

	final static Logger logger = Logger.getLogger(PredictIncome.class);

	public Map<String, List<? extends Report>> incomeReport(PredictionInput input) {

		Map<String, List<? extends Report>> returnMap = new HashMap<>();

		List<IncrementReport> incrementList = new ArrayList<>();
		List<DeductionReport> deductionList = new ArrayList<>();
		List<PredictionReport> predictionList = new ArrayList<>();

		BigDecimal salaryCarryOver = input.getStartingSalary();
		for (int yearCounter = 2000; yearCounter <= 2050; yearCounter++) {

			IncrementReport increment = createIncrementReport(input, yearCounter, salaryCarryOver);
			BigDecimal incrementAmt = calculateSalaryVariation(salaryCarryOver, input.getIncrementPercent(),
					input.getIncrementFrequency());
			increment.setIncrementAmount(incrementAmt);
			incrementList.add(increment);

			DeductionReport deduction = new DeductionReport();
			createDeductionReport(input, salaryCarryOver, yearCounter, deduction);
			BigDecimal deductionAmt = calculateSalaryVariation(salaryCarryOver, input.getDeductionInIncome(),
					input.getDeductionFrequency());
			deduction.setDeductionAmount(deductionAmt);
			deductionList.add(deduction);

			// PredictionOutput
			PredictionReport prediction = new PredictionReport();
			createPrediction(salaryCarryOver, yearCounter, incrementAmt, deductionAmt, prediction);

			// Find prediction
			BigDecimal actualIncrement = incrementAmt.subtract(deductionAmt);
			double salaryGrowth = actualIncrement.multiply(new BigDecimal(100)).divide(salaryCarryOver).doubleValue();
			prediction.setSalaryGrowth(salaryGrowth);
			predictionList.add(prediction);

			salaryCarryOver = salaryCarryOver.add(actualIncrement);
		}

		returnMap.put("IncrementReport", incrementList);
		returnMap.put("DeductionReport", deductionList);
		returnMap.put("PredictionReport", predictionList);

		return returnMap;
	}

	/**
	 * @param salaryCarryOver
	 * @param yearCounter
	 * @param incrementAmt
	 * @param deductionAmt
	 * @param prediction
	 */
	private void createPrediction(BigDecimal salaryCarryOver, int yearCounter, BigDecimal incrementAmt,
			BigDecimal deductionAmt, PredictionReport prediction) {
		prediction.setYear(yearCounter);
		prediction.setStartingSalary(salaryCarryOver);
		prediction.setDeductionAmount(deductionAmt);
		prediction.setIncrementAmount(incrementAmt);
	}

	/**
	 * @param input
	 * @param salaryCarryOver
	 * @param yearCounter
	 * @param deduction
	 */
	private void createDeductionReport(PredictionInput input, BigDecimal salaryCarryOver, int yearCounter,
			DeductionReport deduction) {
		deduction.setYear(yearCounter);
		deduction.setStartingSalary(salaryCarryOver);
		deduction.setNoOfDeductions(input.getIncrementFrequency());
		deduction.setDeductionPercent(input.getIncrementPercent());
	}

	private IncrementReport createIncrementReport(PredictionInput input, int yearCounter, BigDecimal salaryCarryOver) {

		IncrementReport increment = new IncrementReport();
		increment.setYear(yearCounter);
		increment.setStartingSalary(salaryCarryOver);
		increment.setNoOfIncrements(input.getIncrementFrequency());
		increment.setIncrementPercent(input.getIncrementPercent());

		return increment;
	}

	/**
	 * @param input
	 * @return
	 */
	private BigDecimal calculateSalaryVariation(BigDecimal salary, int incrementPercent, int frequency) {
		BigDecimal yearlyIncrment = BigDecimal.ZERO;
		// 10/100 * 10000
		BigDecimal incrementOneTime = salary.multiply(new BigDecimal(incrementPercent)).divide(new BigDecimal(100));
		yearlyIncrment = incrementOneTime.multiply(BigDecimal.valueOf(frequency));
		return yearlyIncrment;
	}

}
