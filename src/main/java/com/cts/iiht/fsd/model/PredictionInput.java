package com.cts.iiht.fsd.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor	
public class PredictionInput extends Report {
	private BigDecimal startingSalary;
	private int incrementPercent;
	// Quarterly/Half-Yearly/Annually
	private int incrementFrequency;
	private int deductionInIncome;
	// Quarterly/Half-Yearly/Annually
	private int deductionFrequency;
	private int predictionFreq;
	
	@Override
	public String toString() {
		return "PredictionInput [startingSalary=" + startingSalary.setScale(2, RoundingMode.HALF_UP)
				+ ", incrementPercent=" + incrementPercent
				+ ", incrementFrequency=" + incrementFrequency + ", deductionInIncome=" + deductionInIncome
				+ ", deductionFrequency=" + deductionFrequency + ", predictionFreq=" + predictionFreq + "]";
	}
	
	
}