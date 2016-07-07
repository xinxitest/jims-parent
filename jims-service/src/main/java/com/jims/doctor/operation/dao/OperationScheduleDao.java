/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.operation.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.common.web.impl.BaseDto;
import com.jims.operation.entity.OperationSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 手术安排DAO接口
 * @author pq
 * @version 2016-05-12
 */
@MyBatisDao
public interface OperationScheduleDao extends CrudDao<OperationSchedule> {
    /**
     * 通过病人ID，住院ID拿到病人本次住院的手术最大的申请号
     * @param patientId
     * @param visitId
     * @return
     */
    public String getScheduleId(@Param(value = "patientId")String patientId,@Param(value = "visitId")String visitId);

    /**
     * 通过病人Id、住院Id拿到病人本次住院的手术安排
     * @param patientId
     * @param visitId
     * @return
     */
    public OperationSchedule getSchedule(@Param(value = "patientId")String patientId,@Param(value = "visitId")String visitId,@Param("clinicId")String clinicId);


    /**
     * 查询门诊手术确认的列表
     * @param operationSchedule
     * @author pq
     * @return
     */
    public List<BaseDto> findOperation(@Param(value = "scheduledDateTime")String scheduledDateTime,@Param(value = "operatingRoom")String operatingRoom);

    /**
     * 确认门诊手术
     * @param id
     * @author pq
     * @return
     */
    public int confrimOperation(OperationSchedule operationSchedule);
}