/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 病历文书--入院记录Entity
 * @author zhaonig
 * @version 2016-04-20
 */
@XmlRootElement
public class ElectronEnterHospital extends DataEntity<ElectronEnterHospital> {
	private static final long serialVersionUID = 1L;
	private String zhuyuanId;		// 住院信息表外键
	private String patientId;		// 病人信息表外键
	private String zhusu;		// 主诉
	private String xianbingshi;		// 现病史
	private String fabingqingkuang;		// 发病后一般情况
	private String jiwangshi;		// 既往史
	private String gerenshi;		// 个人史
	private String hunyushi;		// 婚育史
	private String jiazushi;		// 家族史
	private String tiwen;		// 体温
	private String maibo;		// 脉搏
	private String huxi;		// 呼吸
	private String xueya;		// 血压
	private String zhuankeqingkuang;		// 专科情况
	private String fuzhujiancha;		// 辅助检查
	private String chubuzhenduan;		// 初步诊断
	private String xiuzhengzhenduan;		// 修正诊断
	private String zhuguanyisheng;		// 主管医生
	private String shangjiyishi;		// 上级医师
	private Date riqi;		// 日期
	private String bingshichenshuzhe;		// 病史陈述者
	private String yuejingshi;		// 月经史
	private String tigejiancha;		// 体格检查
	private String xiaojieyufenxi;		// 小结与分析
	private Date caijiriqi;		// 病史采集日期
	private String isdepend;		// 病史陈述者是否可靠
	
	public ElectronEnterHospital() {
		super();
	}

	public ElectronEnterHospital(String id){
		super(id);
	}

	@Length(min=1, max=64, message="住院信息表外键长度必须介于 1 和 64 之间")
	public String getZhuyuanId() {
		return zhuyuanId;
	}

	public void setZhuyuanId(String zhuyuanId) {
		this.zhuyuanId = zhuyuanId;
	}
	
	@Length(min=1, max=64, message="病人信息表外键长度必须介于 1 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public String getZhusu() {
		return zhusu;
	}

	public void setZhusu(String zhusu) {
		this.zhusu = zhusu;
	}
	
	public String getXianbingshi() {
		return xianbingshi;
	}

	public void setXianbingshi(String xianbingshi) {
		this.xianbingshi = xianbingshi;
	}
	
	public String getFabingqingkuang() {
		return fabingqingkuang;
	}

	public void setFabingqingkuang(String fabingqingkuang) {
		this.fabingqingkuang = fabingqingkuang;
	}
	
	public String getJiwangshi() {
		return jiwangshi;
	}

	public void setJiwangshi(String jiwangshi) {
		this.jiwangshi = jiwangshi;
	}
	
	public String getGerenshi() {
		return gerenshi;
	}

	public void setGerenshi(String gerenshi) {
		this.gerenshi = gerenshi;
	}
	
	public String getHunyushi() {
		return hunyushi;
	}

	public void setHunyushi(String hunyushi) {
		this.hunyushi = hunyushi;
	}
	
	public String getJiazushi() {
		return jiazushi;
	}

	public void setJiazushi(String jiazushi) {
		this.jiazushi = jiazushi;
	}
	
	@Length(min=0, max=100, message="体温长度必须介于 0 和 100 之间")
	public String getTiwen() {
		return tiwen;
	}

	public void setTiwen(String tiwen) {
		this.tiwen = tiwen;
	}
	
	@Length(min=0, max=100, message="脉搏长度必须介于 0 和 100 之间")
	public String getMaibo() {
		return maibo;
	}

	public void setMaibo(String maibo) {
		this.maibo = maibo;
	}
	
	@Length(min=0, max=100, message="呼吸长度必须介于 0 和 100 之间")
	public String getHuxi() {
		return huxi;
	}

	public void setHuxi(String huxi) {
		this.huxi = huxi;
	}
	
	@Length(min=0, max=200, message="血压长度必须介于 0 和 200 之间")
	public String getXueya() {
		return xueya;
	}

	public void setXueya(String xueya) {
		this.xueya = xueya;
	}
	
	public String getZhuankeqingkuang() {
		return zhuankeqingkuang;
	}

	public void setZhuankeqingkuang(String zhuankeqingkuang) {
		this.zhuankeqingkuang = zhuankeqingkuang;
	}
	
	public String getFuzhujiancha() {
		return fuzhujiancha;
	}

	public void setFuzhujiancha(String fuzhujiancha) {
		this.fuzhujiancha = fuzhujiancha;
	}
	
	public String getChubuzhenduan() {
		return chubuzhenduan;
	}

	public void setChubuzhenduan(String chubuzhenduan) {
		this.chubuzhenduan = chubuzhenduan;
	}
	
	public String getXiuzhengzhenduan() {
		return xiuzhengzhenduan;
	}

	public void setXiuzhengzhenduan(String xiuzhengzhenduan) {
		this.xiuzhengzhenduan = xiuzhengzhenduan;
	}
	
	@Length(min=0, max=100, message="主管医生长度必须介于 0 和 100 之间")
	public String getZhuguanyisheng() {
		return zhuguanyisheng;
	}

	public void setZhuguanyisheng(String zhuguanyisheng) {
		this.zhuguanyisheng = zhuguanyisheng;
	}
	
	@Length(min=0, max=100, message="上级医师长度必须介于 0 和 100 之间")
	public String getShangjiyishi() {
		return shangjiyishi;
	}

	public void setShangjiyishi(String shangjiyishi) {
		this.shangjiyishi = shangjiyishi;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRiqi() {
		return riqi;
	}

	public void setRiqi(Date riqi) {
		this.riqi = riqi;
	}
	
	@Length(min=0, max=100, message="病史陈述者长度必须介于 0 和 100 之间")
	public String getBingshichenshuzhe() {
		return bingshichenshuzhe;
	}

	public void setBingshichenshuzhe(String bingshichenshuzhe) {
		this.bingshichenshuzhe = bingshichenshuzhe;
	}
	
	public String getYuejingshi() {
		return yuejingshi;
	}

	public void setYuejingshi(String yuejingshi) {
		this.yuejingshi = yuejingshi;
	}
	
	public String getTigejiancha() {
		return tigejiancha;
	}

	public void setTigejiancha(String tigejiancha) {
		this.tigejiancha = tigejiancha;
	}
	
	public String getXiaojieyufenxi() {
		return xiaojieyufenxi;
	}

	public void setXiaojieyufenxi(String xiaojieyufenxi) {
		this.xiaojieyufenxi = xiaojieyufenxi;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCaijiriqi() {
		return caijiriqi;
	}

	public void setCaijiriqi(Date caijiriqi) {
		this.caijiriqi = caijiriqi;
	}
	
	@Length(min=0, max=50, message="病史陈述者是否可靠长度必须介于 0 和 50 之间")
	public String getIsdepend() {
		return isdepend;
	}

	public void setIsdepend(String isdepend) {
		this.isdepend = isdepend;
	}
	
}