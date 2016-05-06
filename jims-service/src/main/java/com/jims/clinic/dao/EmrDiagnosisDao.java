/**
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">EMR</a> All rights reserved.
 */
package com.jims.clinic.dao;



import com.jims.clinic.entity.EmrDiagnosis;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 诊断DAO接口
 * @author zhangyao
 * @version 2015-12-27
 */
@MyBatisDao
public interface EmrDiagnosisDao extends CrudDao<EmrDiagnosis> {
    /**
     * 通过父级Id查找诊断结果集
     * @param
     * @return
     */
    public List<EmrDiagnosis> findAllListByParent(@Param("parent") String parent);

    /**
     * 根据诊断父ID查询啊子集
     * @param id
     * @return
     */
    public List<EmrDiagnosis> findListChildren(@Param("id") String id);


    /**
     * 根据父类查询所有诊断
     * @param parent
     * @return
     */
    public List<EmrDiagnosis> findAllDiagby(@Param("parent") String parent);

    /**根据父类查询对应的诊断类型
     *
     * @param parent
     * @param type
     * @return
     */
    public List<EmrDiagnosis> findAllListByType(@Param("parent") String parent, @Param("type") String type);
	
}