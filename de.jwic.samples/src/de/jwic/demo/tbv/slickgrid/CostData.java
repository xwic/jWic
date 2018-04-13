/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.demo.tbv.slickgrid.UserPojo 
 */
package de.jwic.demo.tbv.slickgrid;

import java.io.Serializable;

/**
 * @author Adrian Ionescu
 */
public class CostData implements Serializable {

	private static final long serialVersionUID = -7221680577288515527L;

	private String spendType;
	private String itemName;
	private boolean internal;
	private boolean approved;
	private boolean paid;
	private double rate;
	private String uom;
	private double may;
	private double june;
	private double july;
	private double august;
	
	/**
	 * @param spendType
	 * @param itemName
	 * @param internal
	 * @param approved
	 * @param paid
	 * @param rate
	 * @param uom
	 * @param may
	 * @param june
	 * @param july
	 * @param august
	 */
	public CostData(String spendType, String itemName, boolean internal, boolean approved, boolean paid, double rate, String uom,
			double may, double june, double july, double august) {
		super();
		this.spendType = spendType;
		this.itemName = itemName;
		this.internal = internal;
		this.approved = approved;
		this.paid = paid;
		this.rate = rate;
		this.uom = uom;
		this.may = may;
		this.june = june;
		this.july = july;
		this.august = august;
	}

	/**
	 * @return the spendType
	 */
	public String getSpendType() {
		return spendType;
	}

	/**
	 * @param spendType the spendType to set
	 */
	public void setSpendType(String spendType) {
		this.spendType = spendType;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}

	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}

	/**
	 * @return the may
	 */
	public double getMay() {
		return may;
	}

	/**
	 * @param may the may to set
	 */
	public void setMay(double may) {
		this.may = may;
	}

	/**
	 * @return the june
	 */
	public double getJune() {
		return june;
	}

	/**
	 * @param june the june to set
	 */
	public void setJune(double june) {
		this.june = june;
	}

	/**
	 * @return the july
	 */
	public double getJuly() {
		return july;
	}

	/**
	 * @param july the july to set
	 */
	public void setJuly(double july) {
		this.july = july;
	}

	/**
	 * @return the august
	 */
	public double getAugust() {
		return august;
	}

	/**
	 * @param august the august to set
	 */
	public void setAugust(double august) {
		this.august = august;
	}


	/**
	 * @return the internal
	 */
	public boolean isInternal() {
		return internal;
	}


	/**
	 * @param internal the internal to set
	 */
	public void setInternal(boolean internal) {
		this.internal = internal;
	}


	/**
	 * @return the approved
	 */
	public boolean isApproved() {
		return approved;
	}


	/**
	 * @param approved the approved to set
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	/**
	 * @return the paid
	 */
	public boolean isPaid() {
		return paid;
	}


	/**
	 * @param paid the paid to set
	 */
	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}
