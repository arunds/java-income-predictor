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
public class DeductionReport extends Report {
	private int year;
	private BigDecimal startingSalary;
	// Quarterly/Half-Yearly/Annually
	private int noOfDeductions;
	private int deductionPercent;
	private BigDecimal deductionAmount;

	@Override
	public String toString() {
		return "DeductionReport [year=" + year + ", startingSalary=" + startingSalary.setScale(2, RoundingMode.HALF_UP)
				+ ", noOfDeductions=" + noOfDeductions + ", deductionPercent=" + deductionPercent + ", deductionAmount="
				+ deductionAmount.setScale(2, RoundingMode.HALF_UP) + "]";
	}

}