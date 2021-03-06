package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
* 录入申请Entity
* @author yangruidong
* @version 2016-07-04
*/
public class DrugProvideApplication extends DataEntity<DrugProvideApplication> {

    private static final long serialVersionUID = 1L;
    private String applicantStorage;  // 请领库房
    private String provideStorage;  // 发放库房
    private Integer itemNo;  // 项目序号
    private String drugCode;  // 药品代码
    private String drugSpec;  // 规格
    private String packageSpec;  // 包装规格
    private Double quantity;  // 数量
    private String packageUnits;  // 包装单位
    private Date enterDateTime;  // 申请日期
    private String firmId;  // 厂家标识
    private String batchNo;  // 批号
    private String documentNo;  // 申请单据号
    private Double noProvideQuantity;  // 未发放数量
    private String flag;  // 是否发放  0  未发放   1  未全部发放  2  全部发放
    private String subStorage;  // 发放库房子单位
    private String applicantStorageSub;  // 请领库房子单位
    private String orgId;     //组织机构ID
    private String units;   //单位


    private String drugName;   //名称
    private String supplierId;   //厂家
    private String label;     //包单位
    private String storageName;  //请领单位名称
    private Double tradePrice;		// 市场批发价
    private Double retailPrice;		// 市场零售价
    private Double stock;
    private String drugStockId;

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public DrugProvideApplication() {
        super();
    }

    public DrugProvideApplication(String id) {
        super(id);
    }

    public  String getApplicantStorage() {
        return this.applicantStorage;
    }

    public void setApplicantStorage(String applicantStorage) {
        this.applicantStorage = applicantStorage;
    }

    public  String getProvideStorage() {
        return this.provideStorage;
    }

    public void setProvideStorage(String provideStorage) {
        this.provideStorage = provideStorage;
    }

    public  Integer getItemNo() {
        return this.itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public  String getDrugCode() {
        return this.drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public  String getDrugSpec() {
        return this.drugSpec;
    }

    public void setDrugSpec(String drugSpec) {
        this.drugSpec = drugSpec;
    }

    public  String getPackageSpec() {
        return this.packageSpec;
    }

    public void setPackageSpec(String packageSpec) {
        this.packageSpec = packageSpec;
    }

    public  Double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public  String getPackageUnits() {
        return this.packageUnits;
    }

    public void setPackageUnits(String packageUnits) {
        this.packageUnits = packageUnits;
    }

    public  Date getEnterDateTime() {
        return this.enterDateTime;
    }

    public void setEnterDateTime(Date enterDateTime) {
        this.enterDateTime = enterDateTime;
    }

    public  String getFirmId() {
        return this.firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public  String getBatchNo() {
        return this.batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public  String getDocumentNo() {
        return this.documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public  Double getNoProvideQuantity() {
        return this.noProvideQuantity;
    }

    public void setNoProvideQuantity(Double noProvideQuantity) {
        this.noProvideQuantity = noProvideQuantity;
    }

    public  String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public  String getSubStorage() {
        return this.subStorage;
    }

    public void setSubStorage(String subStorage) {
        this.subStorage = subStorage;
    }

    public  String getApplicantStorageSub() {
        return this.applicantStorageSub;
    }

    public void setApplicantStorageSub(String applicantStorageSub) {
        this.applicantStorageSub = applicantStorageSub;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public String getDrugStockId() {
        return drugStockId;
    }

    public void setDrugStockId(String drugStockId) {
        this.drugStockId = drugStockId;
    }
}