/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.cliniIcnspect.dao;

import com.jims.exam.entity.ExamAppoints;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 检查预约记录DAO接口
 *
 * @author zhaoning
 * @version 2016-04-25
 */
@MyBatisDao
public interface ExamAppointsDao extends CrudDao<ExamAppoints> {
    /**
     * 查询检查预约记录
     *
     * @param patientId
     * @return
     */
    public List<ExamAppoints> getExamAppionts(String patientId);

    /**
     * 保存预约记录
     *
     * @param examAppoints
     * @return
     */
    public int saveExamAppionts(ExamAppoints examAppoints);

    /**
     * 删除预约记录
     *
     * @param id
     * @return
     */
    public Integer deleteExamAppionts(String id);

    /**
     * 获得最大的申请序号
     *
     * @return
     */
    public Integer getMaxExamNo();

    /**
     * 检查确认时，更新，检查预约
     * @param appointsId
     * @author zhaoning
     */
    public void updateAppoints(@Param("appointsId")String appointsId);
}