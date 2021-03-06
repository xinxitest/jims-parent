/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.OutpOrders;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 门诊医嘱记录DAO接口
 *
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface OutpOrdersDao extends CrudDao<OutpOrders> {
    /**
     * 保存门诊医嘱主记录
     *
     * @param outpOrders
     * @return
     */
    public void saveOutpOrders(OutpOrders outpOrders);


    /**
     * 查询序列号
     *
     * @return
     */
    public String getSerialNo();

    /**
     * 根据就诊序号就诊日期查询门诊记录
     *
     * @param outpOrders
     * @return
     */
    public List<OutpOrders> findListFy(OutpOrders outpOrders);

    /***
     * 删除医嘱记录
     * @param serialNo
     * @return
     */
    public int deleteOutpOrders(@Param("serialNo")String serialNo);

    /**
     * 通过clinicId拿到门诊医嘱记录
     * pq
     */
    public OutpOrders getByClinicId(@Param(value = "clinicId")String clinicId);
}