/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import java.util.Date;

/**
 * 生成号表Entity
 * @author zhaoning
 * @version 2016-05-18
 */
public class ClinicForRegist extends DataEntity<ClinicForRegist> {
	
	private static final long serialVersionUID = 1L;
	private Date clinicDate;		// 门诊日期
	private String clinicLabel;		// 号别
	private String timeDesc;		// 门诊时间描述
	private Integer registrationLimits;		// 限号数
	private Integer appointmentLimits;		// 限预约号数
	private Integer currentNo;		// 当前号
	private Integer registrationNum;		// 当日已挂号数
	private Integer appointmentNum;		// 已预约号数
	private Double registPrice;		// 挂号费标准
	
	public ClinicForRegist() {
		super();
	}

	public ClinicForRegist(String id){
		super(id);
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getClinicDate() {
		return clinicDate;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public void setClinicDate(Date clinicDate) {
		this.clinicDate = clinicDate;
	}
	
	@Length(min=1, max=16, message="号别长度必须介于 1 和 16 之间")
	public String getClinicLabel() {
		return clinicLabel;
	}

	public void setClinicLabel(String clinicLabel) {
		this.clinicLabel = clinicLabel;
	}
	
	@Length(min=1, max=8, message="门诊时间描述长度必须介于 1 和 8 之间")
	public String getTimeDesc() {
		return timeDesc;
	}

	public void setTimeDesc(String timeDesc) {
		this.timeDesc = timeDesc;
	}
	
	public Integer getRegistrationLimits() {
		return registrationLimits;
	}

	public void setRegistrationLimits(Integer registrationLimits) {
		this.registrationLimits = registrationLimits;
	}
	
	public Integer getAppointmentLimits() {
		return appointmentLimits;
	}

	public void setAppointmentLimits(Integer appointmentLimits) {
		this.appointmentLimits = appointmentLimits;
	}
	
	public Integer getCurrentNo() {
		return currentNo;
	}

	public void setCurrentNo(Integer currentNo) {
		this.currentNo = currentNo;
	}
	
	public Integer getRegistrationNum() {
		return registrationNum;
	}

	public void setRegistrationNum(Integer registrationNum) {
		this.registrationNum = registrationNum;
	}
	
	public Integer getAppointmentNum() {
		return appointmentNum;
	}

	public void setAppointmentNum(Integer appointmentNum) {
		this.appointmentNum = appointmentNum;
	}
	
	public Double getRegistPrice() {
		return registPrice;
	}

	public void setRegistPrice(Double registPrice) {
		this.registPrice = registPrice;
	}
	
}