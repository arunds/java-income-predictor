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
public class PredictionReport extends Report {
	private int year;
	private BigDecimal startingSalary;
	private BigDecimal incrementAmount;
	private BigDecimal deductionAmount;
	private double salaryGrowth;

	@Override
	public String toString() {
		return "PredictionReport [year=" + year + ", startingSalary=" + startingSalary.setScale(2, RoundingMode.HALF_UP)
				+ ", incrementAmount=" + incrementAmount.setScale(2, RoundingMode.HALF_UP) + ", deductionAmount="
				+ deductionAmount.setScale(2, RoundingMode.HALF_UP) + ", salaryGrowth=" + salaryGrowth + "% ]";
	}

}