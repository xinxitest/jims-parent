package com.jims.exam.api;

import com.jims.exam.entity.ExamItems;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 * 检查项目Api接口
 * @author zhaoning
 * @version 2016-04-25
 */
public interface ExamItemsServiceApi {
    //检查项目保存
    public void saveExamItem(ExamItems examItems);

    /**
     * 根据申请序号查询项目
     */
    public List<ExamItems> loadExamItems(String examNo);

    /**
     * 删除检查项目
     * @param examNo
     */
    public Integer deleteItems(String examNo);

    /**
     * 通过主记录id获取检查子项
     * @param appointsId
     * @return
     */
    public List<ExamItems> getItemName(String appointsId);

}
