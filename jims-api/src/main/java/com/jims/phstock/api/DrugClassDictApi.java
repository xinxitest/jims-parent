package com.jims.phstock.api;

import com.jims.phstock.entity.DrugClassDict;

import java.util.List;

/**
 * 提供药品类别维护的接口
 * Created by 赵铁强 on 2016/5/9.
 */
public interface DrugClassDictApi {

    /***
     * 获取药品类别
     * @return 药品类别集合
     * @author ztq
     */
    public List<DrugClassDict> listDrugClassDict() ;

    /***
     * 获取组织机构的某一大类的所有亚类
     * @param parentId 大类ID
     * @return 返回某一大类的所有亚类
     * @author ztq
     */
    public List<DrugClassDict> listSubClassDict(String parentId) ;


    /**
     * 保存 药品类别
     * @param drugClassDict 药品字典
     * @return
     * @author ztq
     */
    public String saveDrugClassDict(DrugClassDict drugClassDict) ;

    /**
     * 报错多个药品类别
     * @param drugClassDicts 药品类别集合
     * @return
     * @author ztq
     */
    public String saveDrugClassDicts(List<DrugClassDict> drugClassDicts) ;


}
