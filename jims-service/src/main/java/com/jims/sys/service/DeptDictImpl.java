package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.bo.DeptDictBo;
import com.jims.sys.dao.DeptDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.SysCompany;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
@Service(version = "1.0.0")

public class DeptDictImpl implements DeptDictApi {

    @Autowired
    private DeptDictBo deptDictBo;
    /**
     * 查询所有的科室信息
     * @return
     */
    public List<DeptDict> findAllList(String orgId) {
        return deptDictBo.findAllList(orgId);

    }
    /**
     * 查询所有的科室属性的类型
     * @return
     */
    @Override
    public List<DeptDict> findProperty() {
        return deptDictBo.findProperty();
    }

    /**
     * 查询所有的上级科室
     * @return
     */
    @Override
    public List<DeptDict> findParent() {
        return deptDictBo.findParent();
    }

    /**
     * 查询某个机构的上级科室
     * @return
     */
    public List<DeptDict> findListParent(String orgId){
        return deptDictBo.findListParent(orgId) ;
    }

    /**
     * 保存科室
     * @param deptDict
     * @return
     */
    @Override
    public String save(DeptDict deptDict) {
        return deptDictBo.save(deptDict);
    }

    @Override
    public String delete(String ids) {
        return deptDictBo.delete(ids);
    }

    /**
     * 查询科室代码下的所以科室
     * @return
     */
    public List<DeptDict> findListByCode(String code){
        return deptDictBo.findListByCode(code);
    }

    /**
     * 检索科室
     * @param dept
     * @return
     */
    public List<DeptDict> findList(DeptDict dept){
        return deptDictBo.findList(dept);
    }
}
