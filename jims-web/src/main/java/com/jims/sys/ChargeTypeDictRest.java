package com.jims.sys;

import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.ChargeTypeDictApi;
import com.jims.sys.entity.ChargeTypeDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Created by Administrator on 2016/4/20.
 */
@Component
@Produces("application/json")
@Path("ChargeTypeDict")
public class ChargeTypeDictRest {

    @Autowired
    private ChargeTypeDictApi chargeTypeDictApi;

    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<ChargeTypeDict> page=chargeTypeDictApi.findPage(new Page<ChargeTypeDict>(request,response),new ChargeTypeDict());
        PageData<ChargeTypeDict> pageData=new PageData<ChargeTypeDict>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public ChargeTypeDict get(String id) {
        ChargeTypeDict chargeTypeDict = chargeTypeDictApi.get(id);
        return chargeTypeDict;
    }

    /**
     * 保存修改方法
     * @param chargeTypeDict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(ChargeTypeDict chargeTypeDict){
        String num=chargeTypeDictApi.save(chargeTypeDict);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids){
        String num=chargeTypeDictApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
