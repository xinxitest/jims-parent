package com.jims.clinic.api;

import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.clinic.entity.ClinicVsCharge;
import com.jims.common.persistence.Page;
import com.jims.sys.vo.PriceDictListVo;

import java.util.List;

/**
 * Created by lgx on 2016/4/28.
 */
public interface ClinicItemApi {

    /**
     * 获取单条临床诊疗项目数据
     * @param id
     * @return
     */
    public ClinicItemDict get(String id);

    /**
     * 获取临床诊疗项目列表数据
     * @param entity
     * @return
     */
    public List<ClinicItemDict> findList(ClinicItemDict entity);

    /**
     * 编码或名称已存在个数
     * @return
     */
    public boolean codeOrNameHas(ClinicItemDict entity);

    /**
     * 查询临床诊疗项目分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<ClinicItemDict> findPage(Page<ClinicItemDict> page, ClinicItemDict entity);

    /**
     * 保存临床诊疗项目数据（插入或更新）
     * @param entity
     */
    public String save(ClinicItemDict entity) ;

    /**
     * 批量保存临床诊疗项目数据（插入或更新）
     * @param entityList
     * @return 成功个数
     */
    public String save(List<ClinicItemDict> entityList);

    /**
     * 删除数据
     * @param ids,多个id以逗号隔开
     */
    public String delete(String ids) ;

    /**
     * 删除诊疗项目数据以及所属名称和价表对照
     * @param ids,多个id以逗号隔开
     * @return
     */
    public String deleteCascade(String ids);

    /**
     * 删除诊疗项目数据以及所属名称和价表对照
     * @param entity
     * @return
     */
    public String deleteCascade(ClinicItemDict entity);

    /**
     * 获取临床诊疗项目名称（正/别名）信息
     * @param entity
     * @return
     */
    public List<ClinicItemNameDict> findNameList(ClinicItemDict entity);

    /**
     * 批量保存临床诊疗项目名称(正/别名)数据（插入或更新）
     * @param entityList
     * @return
     */
    public String saveNameList(List<ClinicItemNameDict> entityList);

    /**
     * 保存临床诊疗项目名称(正/别名)数据（插入或更新）
     * @param entity
     * @return
     */
    public String save(ClinicItemNameDict entity);

    /**
     * 删除临床诊疗项目名称(正/别名)数据
     * @param ids ,多个id以逗号隔开
     * @return
     */
    public String deleteName(String ids);

    /**
     * 删除临床诊疗项目所有名称(正/别名)数据
     * @param entity
     * @return
     */
    public String delete(ClinicItemNameDict entity);

    /**
     * 删除临床诊疗项目所有名称(正/别名)数据
     * @param entity
     * @return
     */
    public String deleteName(ClinicItemDict entity);

    /**
     * 获取临床诊疗与价表对照信息
     * @param entity
     * @return
     */
    public List<ClinicVsCharge> findVsList(ClinicItemDict entity);

    /**
     * 保存临床诊疗与价表对照数据（插入或更新）
     * @param entity
     * @return
     */
    public String save(ClinicVsCharge entity);

    /**
     * 批量保存临床诊疗与价表对照数据（插入或更新）
     * @param entityList
     * @return
     */
    public String saveVsList(List<ClinicVsCharge> entityList);

    /**
     * 删除临床诊疗与价表对照数据
     * @param entity
     * @return
     */
    public String delete(ClinicVsCharge entity);

    /**
     * 删除临床诊疗与价表对照数据
     * @param ids,多个id以逗号隔开
     * @return
     */
    public String deleteVs(String ids);

    /**
     * 删除临床诊疗与价表对照数据
     * @param entity
     * @return
     */
    public String deleteVs(ClinicItemDict entity);

    /**
     * 保存临床诊疗与价表对照数据
     * @param priceDictListVo
     * @return
     */
    public String saveDictList(PriceDictListVo priceDictListVo);

    /**
     * 根据组织机构ID和诊疗项目类别获取诊疗项目名称
     * @param orgId 组织机构Id
     * @param clinicClass 诊疗项目名称
     * @author ztq
     * @return
     */
    public List<ClinicItemDict> findListByOrgIdAndClinicClass(String orgId, String clinicClass);
}
