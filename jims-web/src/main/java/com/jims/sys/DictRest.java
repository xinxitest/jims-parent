package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.DictServiceApi;
import com.jims.sys.entity.Dict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;
import sun.org.mozilla.javascript.internal.Undefined;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;

import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by heren on 2016/4/5.
 */
@Component
@Produces("application/json")
@Path("dict")
public class DictRest {

    @Reference(version = "1.0.0")
    private DictServiceApi dictService;

    /**
     * 异步加载页面左侧表格:字典表的类型和描述列表
     *
     * @param request  请求
     * @param response 响应
     * @return 字典表type和description两个字段的list集合
     * @author fengyuguang
     */
    @GET
    @Path("type-description-list")
    public List<Dict> leftList(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<Dict> list = dictService.leftList();
        return list;
    }

    /**
     * 异步加载页面右侧表格:字典表的键值列表
     *
     * @param type 字典表类型
     * @return 字典表List集合
     * @author fengyuguang
     */
    @GET
    @Path("label-value-list")
    public List<Dict> rightList(@QueryParam("type") String type) {
        return dictService.rightList(type);
    }

    /**
     * 保存多条增删改数据
     *
     * @param beanChangeVo 多条增删改数据的集合
     * @return 响应数据
     * @author fengyuguang
     */
    @POST
    @Path("merge")
    public StringData merge(BeanChangeVo<Dict> beanChangeVo) {
        String num = dictService.merge(beanChangeVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }

    /**
     * 根据类型或描述模糊查询
     *
     * @param type        类型
     * @param description 描述
     * @return 类型描述列表
     * @author fengyuguang
     */
    @GET
    @Path("search")
    public List<Dict> select(@QueryParam("type") String type, @QueryParam("description") String description) {
        return dictService.select(type, description);
    }

    /**
     * 异步加载表格
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Page<Dict> page = dictService.findPage(new Page<Dict>(request, response), new Dict());
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }


    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public Dict get(String id) {
        Dict dict = dictService.get(id);
        return dict;
    }

    /**
     * 保存修改方法
     *
     * @param dict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(Dict dict) {
        String num = dictService.save(dict);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


    /**
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = dictService.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 获取指定类型的数据
     *
     * @param type
     * @return
     */
    @Path("findType")
    @GET
    public List<String> findListType(String type) {
        List<String> data = dictService.findListType(type);
        for (int i = 0; i < data.size(); i++) {
            String b = data.get(i);
            System.out.print("b=" + b);
        }
        return data;
    }

    /**
     * 根据类型检索字典
     *
     * @param type
     * @return
     */
    @GET
    @Path("findListByType")
    public List<Dict> findListByType(@QueryParam("type") String type) {
        return dictService.findList(type);
    }

    /**
     * 获得字典label值
     *
     * @param type
     * @param value
     * @return
     */
    @Path("getLabel")
    @GET
    public String findLabel(String type, String value) {
        return dictService.getLabel(type, value);
    }
}
