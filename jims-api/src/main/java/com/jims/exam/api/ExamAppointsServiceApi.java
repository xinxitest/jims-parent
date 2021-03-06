package com.jims.exam.api;

import com.jims.common.vo.LoginInfo;
import com.jims.exam.entity.ExamAppoints;
import com.jims.common.persistence.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 * 检查预约记录Api接口
 * @author zhaoning
 * @version 2016-04-25
 */
public interface ExamAppointsServiceApi {
    /**
     * 查询字段列表
     * @param page
     * @param examAppoints
     * @return
     */
    public Page<ExamAppoints> findPage(Page<ExamAppoints> page, ExamAppoints examAppoints);

    /**
     * 保存修改数据
     * @param examAppoints
     * @return
     */
    public String save(ExamAppoints examAppoints);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public ExamAppoints get(String id);

    /**
     * 查询检查预约记录
     * @param patientId
     * @return
     */
    public List<ExamAppoints> getExamAppionts(String patientId);


    /**
     * 删除门诊预约记录
     * @param id
     * @return
     */

    public String deleteExamAppionts( String id);

    /**
     * 删除住院记录
     * @param id
     * @return
     */
    public String delectHosExamAppionts(String id);

    /**
     * 获得最大的申请序号
     * @return
     */
    public Integer getMaxExamNo();
    /**
     * 门诊检查申请保存
     * @param
     * @return
     */
    public int batchSave(ExamAppoints examAppoints,LoginInfo loginInfo);

    /**
     * 住院检查申请保存
     * @param examAppoints
     * @return
     */
    public int saveHospitalInspect(ExamAppoints examAppoints,LoginInfo loginInfo);
}

