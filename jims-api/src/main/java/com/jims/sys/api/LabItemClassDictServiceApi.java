package com.jims.sys.api;

import com.jims.sys.entity.LabItemClassDict;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 * 检验项目类别字典Api接口
 * @author xueyx
 * @version 2016-05-05
 */
public interface LabItemClassDictServiceApi {

    /**
     * 查询科室代码下的检验类别
     ** @param检验科室编码 deptCode
     * @return
     */
    public List<LabItemClassDict> findListByDeptCode(String deptCode);
}
