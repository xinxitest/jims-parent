package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.OrgRoleApi;
import com.jims.sys.api.OrgStaffApi;
import com.jims.sys.api.PersionInfoApi;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by yangruidong on 2016/5/31 0024.
 */
@Component
@Produces("application/json")
@Path("org-role")
public class OrgRoleRest {
    @Reference(version = "1.0.0")
    private OrgRoleApi orgRoleApi;

    @GET
    @Path("findAllListByOrgId")
    public List<OrgRole> findAllListByOrgId(@QueryParam("orgId") String orgId)
    {
        return orgRoleApi.findAllList(orgId);
    }

    @GET
    @Path("find-by-name")
    public List<OrgRole> findByRoleName(@QueryParam("roleName")String roleName){
        return orgRoleApi.findByRoleName(roleName);
    }

    /**
     * 保存增删改
     * @param beanChangeVo 角色增删改集合
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("merge")
    public StringData merge(BeanChangeVo<OrgRole> beanChangeVo) {
        List<OrgRole> inserted = beanChangeVo.getInserted();
        String num = orgRoleApi.merge(beanChangeVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }
}
