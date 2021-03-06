package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.web.impl.BaseDto;
import com.jims.register.api.ClinicIndexServiceApi;
import com.jims.register.entity.ClinicForRegist;
import com.jims.register.entity.ClinicIndex;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 * 号别Rest
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("clinicIndex")
public class ClinicIndexRest {
    @Reference(version = "1.0.0")
    private ClinicIndexServiceApi clinicIndexServiceApi;

    /**
     * 查询号别list
     * @return
     * @author zhaoning
     */
    @GET
    @Path("findList")
    public PageData findClinicIndexs(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("deptName")String deptName,
                                              @QueryParam("clinicIndexName")String clinicIndexName,
                                              @QueryParam("doctor")String doctor){
        String orgId= LoginInfoUtils.getPersionInfo(request).getOrgId();
        ClinicIndex clinicIndex=new ClinicIndex();
        clinicIndex.setClinicDept(deptName);
        clinicIndex.setClinicLabel(clinicIndexName);
        clinicIndex.setDoctor(doctor);
        clinicIndex.setOrgId(orgId);
        Page<ClinicIndex> page = clinicIndexServiceApi.findPage(new Page<ClinicIndex>(request, response),clinicIndex);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 保存 号别数据
     * @param clinicIndexList
     * @return
     * @author zhaoning
     */
    @POST
    @Path("saveClinicIndex")
    public StringData saveClinicIndex(List<ClinicIndex> clinicIndexList,@Context HttpServletRequest request){
        String orgId=LoginInfoUtils.getPersionInfo(request).getOrgId();
        StringData data=new StringData();
        data.setCode(clinicIndexServiceApi.saveList(clinicIndexList,orgId));
        return data;
    }

    /**
     * 删除 号别
     * @param id
     * @return
     * @author zhaoning
     */
    @Path("delete")
    @POST
    public StringData deleteClinicIndex(String id){
        StringData data = new StringData();
        data.setCode(clinicIndexServiceApi.delete(id));
        return data;
    }


    /**
     * 获取号别中的费用
     * @param id
     * @return
     */
    @Path("getCost")
    @GET
    public BaseDto getCost(@QueryParam("id")String id){
        BaseDto baseDto=clinicIndexServiceApi.getCost(id);
        return baseDto;
    }
}
