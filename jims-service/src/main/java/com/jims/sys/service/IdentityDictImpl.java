package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.api.IdentityDictApi;
import com.jims.sys.bo.IdentityDictBo;
import com.jims.sys.entity.IdentityDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 身份字典表api接口实现类
 * Created by fyg on 2016/6/21.
 */
@Service(version = "1.0.0")

public class IdentityDictImpl implements IdentityDictApi {

    @Autowired
    private IdentityDictBo identityDictBo;

    /**
     * 查询所有记录
     * @return 身份字典的list集合
     * @author fengyuguang
     */
    public List<IdentityDict> findList(){
        return identityDictBo.findList(new IdentityDict());
    }

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<IdentityDict> beanChangeVo){
        return identityDictBo.merge(beanChangeVo);
    }

    /**
     * 根据身份名称模糊查询记录
     * @param identityName 身份名称
     * @return
     * @author fengyuguang
     */
    public List<IdentityDict> search(String identityName){
        return identityDictBo.search(identityName);
    }
}
