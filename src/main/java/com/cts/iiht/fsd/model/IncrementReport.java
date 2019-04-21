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
public class IncrementReport extends Report {
	private int year;
	private BigDecimal startingSalary;
	// Quarterly/Half-Yearly/Annually
	private int noOfIncrements;
	private int incrementPercent;
	private BigDecimal incrementAmount;

	@Override
	public String toString() {

		return "IncrementReport [year=" + year + ", startingSalary=" + startingSalary.setScale(2, RoundingMode.HALF_UP)
				+ ", noOfIncrements=" + noOfIncrements + ", incrementPercent=" + incrementPercent + ", incrementAmount="
				+ incrementAmount.setScale(2, RoundingMode.HALF_UP) + "]";
	}

}