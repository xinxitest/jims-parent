package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugCodingRuleApi;
import com.jims.phstock.entity.DrugCodingRule;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 */
@Component
@Produces("application/json")
@Path("drug-code")
public class DrugCodingRuleRest {

    @Reference(version = "1.0.0")
    private DrugCodingRuleApi drugCodingRuleApi;

    @Path("findAll")
    @GET
    public List<DrugCodingRule> findAllList() {
        return drugCodingRuleApi.findAllList();
    }

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    @Path("merge")
    @POST
    public StringData merge(BeanChangeVo<DrugCodingRule> beanChangeVo) {
        String num = drugCodingRuleApi.merge(beanChangeVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }

    @Path("save")
    @POST
    public StringData save(List<DrugCodingRule> list) {
        int saveResult = 0;
        for (int i = 0, j = (list != null ? list.size() : 0); i < j; i++) {
            DrugCodingRule drugCodingRule = list.get(i);
            String num = drugCodingRuleApi.save(drugCodingRule);
            saveResult += Integer.valueOf(num);
        }
        StringData stringData = new StringData();
        stringData.setCode(String.valueOf(saveResult));
        stringData.setData("success");
        return stringData;
    }

    @Path("delete")
    @POST
    public StringData delete(String id) {
        String num = drugCodingRuleApi.delete(id);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    //通过编码名称获取编码长度
    @Path("findLevel")
    @GET
    public StringData findLevelWidth(@QueryParam("className")String className){
        DrugCodingRule num = null;
        try {
            className = URLDecoder.decode(className, "UTF-8");
            num = drugCodingRuleApi.findLevelWidth(className);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringData stringData = new StringData();
        stringData.setCode(String.valueOf(num.getLevelWidth()));
        stringData.setData("success");
        return stringData;
    }
}
