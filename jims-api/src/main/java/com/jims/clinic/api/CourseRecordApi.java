package com.jims.clinic.api;

import com.jims.clinic.entity.CourseRecord;

import java.util.List;

/**
 * Created by qinlongxin on 2016/4/20.
 */
public interface CourseRecordApi {
    /**
     * 根据病程id查询病程记录主表信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public CourseRecord get(CourseRecord courseRecord);
    /**
     * 查询病程记录主表分页信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public List<CourseRecord> findPage(CourseRecord courseRecord);
    /**
     * 保存或编辑
     * @author qinlongxin
     * @version 2016/4/20
     */
    public void saveCourseRecord(CourseRecord courseRecord);
}
