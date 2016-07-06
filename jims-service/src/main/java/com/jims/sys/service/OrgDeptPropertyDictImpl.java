/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.DeptPropertyDictApi;
import com.jims.sys.bo.DeptPropertyDictBo;
import com.jims.sys.dao.DictDao;
import com.jims.sys.dao.OrgDeptPropertyDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.vo.OrgDeptPropertyDictVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * 科室属性Service
 *
 * @author yangruidong
 * @version 2016-04-23
 */
@Service(version = "1.0.0")

public class OrgDeptPropertyDictImpl implements DeptPropertyDictApi {

    @Autowired
    private DeptPropertyDictBo deptPropertyDictBo;

    @Override
    public OrgDeptPropertyDict get(String id) {
        return deptPropertyDictBo.get(id);
    }

    @Override
    public Page<OrgDeptPropertyDict> findPage(Page<OrgDeptPropertyDict> page, OrgDeptPropertyDict orgDeptPropertyDict) {
        return deptPropertyDictBo.findPage(page, orgDeptPropertyDict);
    }

    @Override
    public StringData add(OrgDeptPropertyDict orgDeptPropertyDict) {
        orgDeptPropertyDict.preInsert();
        StringData stringData = deptPropertyDictBo.add(orgDeptPropertyDict);
        return stringData;
    }

    /**
     * 查询科室属性信息
     * @param orgDeptPropertyDict
     * @return
     */
    public List<OrgDeptPropertyDict> findList(OrgDeptPropertyDict orgDeptPropertyDict) {
        return deptPropertyDictBo.findList(orgDeptPropertyDict);
    }

    /**
     * 保存  增删改
     *
     * @param orgDeptPropertyDictVo
     * @return
     * @author yangruidong
     */
    @Override
    public List<OrgDeptPropertyDict> saveAll(OrgDeptPropertyDictVo<OrgDeptPropertyDict> orgDeptPropertyDictVo) {
        return deptPropertyDictBo.saveAll(orgDeptPropertyDictVo);
    }

    @Override
    public String delete(String ids) {
        return deptPropertyDictBo.delete(ids);
    }

    /**
     * 根据属性类型查询属性名称
     *
     * @param
     * @return
     */
    @Override
    public List<OrgDeptPropertyDict> findNameByType(String propertyType, String orgId) {
        return deptPropertyDictBo.findNameByType(propertyType, orgId);
    }

    /**
     * 根据属性类型和属性值查询属性名称
     *
     * @param propertyType
     * @param propertyValue
     * @return
     */
    @Override
    public OrgDeptPropertyDict findNameByTypeAndValue(String propertyType, String propertyValue, String orgId) {
        return deptPropertyDictBo.findNameByTypeAndValue(propertyType, propertyValue, orgId);
    }

    /**
     * 查询所有的属性类型
     *
     * @return
     */
    @Override
    public List<OrgDeptPropertyDict> findProperty(String orgId) {
        return deptPropertyDictBo.findProperty(orgId);
    }


    /**
     * 根据条件查询所有的属性信息
     *
     * @param orgDeptPropertyDict
     * @return
     */
    @Override
    public List<OrgDeptPropertyDict> findByCondition(OrgDeptPropertyDict orgDeptPropertyDict) {

        return deptPropertyDictBo.findByCondition(orgDeptPropertyDict);
    }

    /**
     * 查询属性的名称
     *
     * @param propertyType
     * @return
     */
    @Override
    public List<OrgDeptPropertyDict> findName(String propertyType, String orgId) {
        return deptPropertyDictBo.findName(propertyType, orgId);
    }

    /**
     * 查询最大的排序值
     *
     * @return
     */
    @Override
    public OrgDeptPropertyDict findSort(String orgId) {
        return deptPropertyDictBo.findSort(orgId);
    }


}
