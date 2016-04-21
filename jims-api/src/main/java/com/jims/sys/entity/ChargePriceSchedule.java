/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 收费系数Entity
 * @author zhangpeng
 * @version 2016-04-18
 */
public class ChargePriceSchedule extends DataEntity<ChargePriceSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String hospitalId;		// 医院id
	private String chargeType;		// 费别
	private String priceCoeffNumerator;		// 收费系数分子(住院)
	private String priceCoeffDenominator;		// 收费系数分母(住院)
	private String chargeSpecialIndicator;		// 适用特殊收适用特殊收费项目标志
	private String numeratorOutp;		// 收费系数分子(门诊)
	private String denominatorOutp;		// 收费系数分母(分母)
	
	public ChargePriceSchedule() {
		super();
	}

	public ChargePriceSchedule(String id){
		super(id);
	}

	@Length(min=0, max=64, message="医院id长度必须介于 0 和 64 之间")
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	@Length(min=0, max=8, message="费别长度必须介于 0 和 8 之间")
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	
	@Length(min=1, max=2, message="收费系数分子(住院)长度必须介于 1 和 2 之间")
	public String getPriceCoeffNumerator() {
		return priceCoeffNumerator;
	}

	public void setPriceCoeffNumerator(String priceCoeffNumerator) {
		this.priceCoeffNumerator = priceCoeffNumerator;
	}
	
	@Length(min=1, max=2, message="收费系数分母(住院)长度必须介于 1 和 2 之间")
	public String getPriceCoeffDenominator() {
		return priceCoeffDenominator;
	}

	public void setPriceCoeffDenominator(String priceCoeffDenominator) {
		this.priceCoeffDenominator = priceCoeffDenominator;
	}
	
	@Length(min=0, max=1, message="适用特殊收适用特殊收费项目标志长度必须介于 0 和 1 之间")
	public String getChargeSpecialIndicator() {
		return chargeSpecialIndicator;
	}

	public void setChargeSpecialIndicator(String chargeSpecialIndicator) {
		this.chargeSpecialIndicator = chargeSpecialIndicator;
	}
	
	@Length(min=0, max=2, message="收费系数分子(门诊)长度必须介于 0 和 2 之间")
	public String getNumeratorOutp() {
		return numeratorOutp;
	}

	public void setNumeratorOutp(String numeratorOutp) {
		this.numeratorOutp = numeratorOutp;
	}
	
	@Length(min=0, max=1, message="收费系数分母(分母)长度必须介于 0 和 1 之间")
	public String getDenominatorOutp() {
		return denominatorOutp;
	}

	public void setDenominatorOutp(String denominatorOutp) {
		this.denominatorOutp = denominatorOutp;
	}

	
}