/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.ElectronEnterHospital;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 病历文书--入院记录DAO接口
 * @author zhaonig
 * @version 2016-04-20
 */
@MyBatisDao
public interface ElectronEnterHospitalDao extends CrudDao<ElectronEnterHospital> {

    /**
     * 根据病人住院ID查询入院记录
     * @param patVisitId
     * @return
     */
    public ElectronEnterHospital getElectronEnteHos(@Param("patVisitId")String patVisitId);
}