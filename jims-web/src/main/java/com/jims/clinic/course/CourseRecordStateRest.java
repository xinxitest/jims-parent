package com.jims.clinic.course;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.CourseRecordStageApi;
import com.jims.clinic.entity.CourseRecord;
import com.jims.clinic.entity.CourseRecordEachdis;
import com.jims.clinic.entity.CourseRecordStage;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by qinlongxin on 2016/4/22.
 * 阶段小结
 */

@Component
@Produces("application/json")
@Path("courseRecordState")
public class CourseRecordStateRest {
    @Reference( version = "1.0.0")
    private CourseRecordStageApi courseRecordStageApi;
    /**
     * 保存
     */
    @Path("save")
    @POST
    public StringData save(CourseRecordStage courseRecordStage) {
        CourseRecord courseRecord=new CourseRecord();
        courseRecord.setLuruShijian(courseRecordStage.getLuruShijian());
        courseRecord.setType("3");
        courseRecord.setPatientId("16013020");
        courseRecord.setZhuyuanId("c1a84181-c0e0-11e5-8417-0894ef010b21");
        courseRecordStage.setCourseRecord(courseRecord);
        StringData data = new StringData();
        String num = data.getCode();
        if (courseRecordStage != null) {
            num = courseRecordStageApi.saveStage(courseRecordStage);
        }
        data.setCode(num);
        data.setData("success");
        return data;
    }

    /**
     * 获取阶段小结
     */
    @Path("get")
    @POST
    public CourseRecordStage get(String id){
        CourseRecordStage courseRecordStage=courseRecordStageApi.getByCourseId(id);
        return  courseRecordStage;

    }



}
