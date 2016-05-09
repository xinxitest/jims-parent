package com.jims.clinic.api;

import com.jims.clinic.entity.ExamRptPattern;
import com.jims.common.persistence.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public interface ExamRptPatternApi {

    /**
     * 查询字段列表
     * @param page
     * @param examRptPattern
     * @return
     */
    public Page<ExamRptPattern> findPage(Page<ExamRptPattern> page, ExamRptPattern examRptPattern);

    /**
     * 保存修改数据
     * @param examRptPattern
     * @return
     */
    public String save(ExamRptPattern examRptPattern);

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
    public ExamRptPattern get(String id);

    /**
     * 通过 子类 查询数据
     * @param examSubClass
     * @return
     */
    public List getExamRptPattern(String examSubClass);
}
