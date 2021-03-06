package com.jims.clinic.course;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.CourseRecordEachdisApi;
import com.jims.clinic.entity.CourseRecord;
import com.jims.clinic.entity.CourseRecordEachdis;
import com.jims.clinic.entity.CourseRecordStage;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by qinlongxin on 2016/4/21
 */
@Component
@Produces("application/json")
@Path("courseRecordeachdis")
public class CourseRecordeachdisRest{
    @Reference(version = "1.0.0")
    private CourseRecordEachdisApi courseRecordEachdisApi ;
    /**
     * 保存每日病程
     */
    @Path("save")
    @POST
    public StringData save(CourseRecordEachdis courseRecordEachdis) {
        CourseRecord courseRecord=new CourseRecord();
        courseRecord.setType(courseRecordEachdis.getType());
        courseRecord.setLuruShijian(courseRecordEachdis.getLuruShijian());
        courseRecord.setPatientId("16013020");
        courseRecord.setZhuyuanId("c1a84181-c0e0-11e5-8417-0894ef010b21");
        courseRecordEachdis.setCourseRecord(courseRecord);
        StringData data = new StringData();
        String num = data.getCode();
        if (courseRecordEachdis != null) {
            num = courseRecordEachdisApi.saveEachdis(courseRecordEachdis);
        }
        data.setCode(num);
        data.setData("success");
        return data;
    }

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public CourseRecordEachdis get(String id){
        CourseRecordEachdis courseRecordEachdis=courseRecordEachdisApi.getEachdisByCourseRecordId(id);
        return courseRecordEachdis;
    }
}
