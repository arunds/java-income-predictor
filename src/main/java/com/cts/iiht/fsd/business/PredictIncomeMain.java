package com.cts.iiht.fsd.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cts.iiht.fsd.model.DeductionReport;
import com.cts.iiht.fsd.model.IncrementReport;
import com.cts.iiht.fsd.model.PredictionInput;
import com.cts.iiht.fsd.model.PredictionReport;
import com.cts.iiht.fsd.model.Report;

/**
 * This call is the main entry point for the income prediction.
 * @author arunds
 *
 */
public class PredictIncomeMain {

	final static Logger logger = Logger.getLogger(PredictIncome.class);

	public void invokeIncomeReport(PredictionInput input) {

		PredictIncome predictIncome = new PredictIncome();
		Map<String, List<? extends Report>> reportMap = predictIncome.incomeReport(input);

		List<IncrementReport> incrementList = (List<IncrementReport>) reportMap.get("IncrementReport");
		List<DeductionReport> deductionList = (List<DeductionReport>) reportMap.get("DeductionReport");
		List<PredictionReport> predictionList = (List<PredictionReport>) reportMap.get("PredictionReport");

		// Print Increment Report
		logger.info("<------------------- Increment Report ------------------->");
		incrementList.forEach(increment -> {
			if (isReportNeeded(increment.getYear())) {
				logger.info(increment);
			}
		});
		// Print Deduction Report
		logger.info("<------------------- Deduction Report ------------------->");
		deductionList.forEach(deduction -> {
			if (isReportNeeded(deduction.getYear())) {
				logger.info(deduction);
			}
		});
		// Print Prediction Report
		logger.info("<------------------- Prediction Report ------------------->");
		predictionList.forEach(prediction -> {
			if (isReportNeeded(prediction.getYear())) {
				logger.info(prediction);
			}

		});

	}

	/**
	 * @param year
	 * @return
	 */
	private boolean isReportNeeded(int year) {
		return year == 2005 || year == 2010 || year == 2020 || year == 2030 || year == 2050;
	}

	public static void main(String[] args) {

		PredictIncomeMain main = new PredictIncomeMain();
		PredictionInput input = new PredictionInput();
		input.setStartingSalary(new BigDecimal(10000));
		input.setIncrementFrequency(2);
		input.setDeductionFrequency(1);
		input.setIncrementPercent(3);
		input.setDeductionInIncome(2);

		main.invokeIncomeReport(input);

	}

}
