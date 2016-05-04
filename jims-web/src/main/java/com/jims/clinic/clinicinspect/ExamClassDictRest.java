package com.jims.clinic.clinicinspect;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.ExamClassDictApi;
import com.jims.clinic.api.ExamRptPatternApi;
import com.jims.clinic.api.ExamSubclassDictApi;
import com.jims.clinic.entity.ExamClassDict;
import com.jims.clinic.entity.ExamRptPattern;
import com.jims.clinic.entity.ExamSubclassDict;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
@Component
@Produces("application/json")
@Path("examClassDict")
public class ExamClassDictRest {
    @Reference(version = "1.0.0")
    private ExamClassDictApi examClassDictApi;
    @Reference(version = "1.0.0")
    private ExamSubclassDictApi examSubclassDictApi;
    @Reference(version = "1.0.0")
    private ExamRptPatternApi examRptPatternApi;


    @Path("getEx")
    @POST
    public List getEx() {
        List<ExamClassDict> list=examClassDictApi.getEx();
        return list;
    }

    @Path("getExamSubclass")
    @POST
    public List getExamSubclass(String examClassName){
       List<ExamSubclassDict> examSubclassDictList=examSubclassDictApi.getEx(examClassName);
       return examSubclassDictList;
    }

    @Path("getExamRptPattern")
    @POST
    public List getExamRptPattern(String examSubClass){
        List<ExamRptPattern> examRptPatternList=examRptPatternApi.getExamRptPattern(examSubClass);
        return examRptPatternList;
    }
}