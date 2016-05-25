package com.jims.clinic.entity;



import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 待发处方门诊明细Entity
 * @author pq
 * @version 2016-05-24
 */
public class DrugPrescDetailTemp extends DataEntity<DrugPrescDetailTemp> {
	
	private static final long serialVersionUID = 1L;
	private String masterId;		// 代发药处方主表ID
	private String preseId;		// 处方主记录外键
	private Date prescDate;		// 处方日期
	private Integer prescNo;		// 处方号
	private Integer itemNo;		// 项目序号
	private String drugCode;		// 药品代码
	private String drugSpec;		// 药品规格
	private String drugName;		// 药品名称
	private String firmId;		// 厂商标识
	private String packageSpec;		// 包装规格
	private String packageUnits;		// 单位
	private Double quantity;		// 数量
	private Double costs;		// 费用
	private Double payments;		// 实付费用
	private String administration;		// 用法
	private Integer orderNo;		// 医嘱序号
	private Integer orderSubNo;		// 医嘱子序号
	private Double dosageEach;		// 使用剂量
	private String dosageUnits;		// dosage_units
	private String frequency;		// frequency
	private String freqDetail;		// freq_detail
	private String batchNo;		// batch_no
	private Integer abidance;		// abidance
	
	public DrugPrescDetailTemp() {
		super();
	}

	public DrugPrescDetailTemp(String id){
		super(id);
	}


	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}


	public String getPreseId() {
		return preseId;
	}

	public void setPreseId(String preseId) {
		this.preseId = preseId;
	}


	public Date getPrescDate() {
		return prescDate;
	}

	public void setPrescDate(Date prescDate) {
		this.prescDate = prescDate;
	}


	public Integer getPrescNo() {
		return prescNo;
	}

	public void setPrescNo(Integer prescNo) {
		this.prescNo = prescNo;
	}


	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}


	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}


	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}


	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}


	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}


	public String getPackageSpec() {
		return packageSpec;
	}

	public void setPackageSpec(String packageSpec) {
		this.packageSpec = packageSpec;
	}


	public String getPackageUnits() {
		return packageUnits;
	}

	public void setPackageUnits(String packageUnits) {
		this.packageUnits = packageUnits;
	}


	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}


	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}


	public Double getPayments() {
		return payments;
	}

	public void setPayments(Double payments) {
		this.payments = payments;
	}


	public String getAdministration() {
		return administration;
	}

	public void setAdministration(String administration) {
		this.administration = administration;
	}


	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}


	public Integer getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(Integer orderSubNo) {
		this.orderSubNo = orderSubNo;
	}


	public Double getDosageEach() {
		return dosageEach;
	}

	public void setDosageEach(Double dosageEach) {
		this.dosageEach = dosageEach;
	}


	public String getDosageUnits() {
		return dosageUnits;
	}

	public void setDosageUnits(String dosageUnits) {
		this.dosageUnits = dosageUnits;
	}


	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}


	public String getFreqDetail() {
		return freqDetail;
	}

	public void setFreqDetail(String freqDetail) {
		this.freqDetail = freqDetail;
	}


	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public Integer getAbidance() {
		return abidance;
	}

	public void setAbidance(Integer abidance) {
		this.abidance = abidance;
	}

}