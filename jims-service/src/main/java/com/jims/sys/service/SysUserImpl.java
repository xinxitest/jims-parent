package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.JedisUtils;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.SysUserApi;
import com.jims.sys.dao.SysCompanyDao;
import com.jims.sys.dao.SysUserDao;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.io.Serializable;


/**
 * Created by Administrator on 2016/4/14 0014.
 */
@Service(version = "1.0.0")
public class SysUserImpl extends CrudImplService<SysUserDao, SysUser> implements SysUserApi, Serializable {

    @Autowired
    private SysCompanyDao sysCompanyDao;

    /**
     * 用户登录
     *
     * @param sysUser
     * @return
     */
    public SysUser login(SysUser sysUser) {
        SysUser user = dao.login(sysUser);
        return user;
    }



    public SysCompany findNameByOwner(String loginName) {
        SysCompany sysCompany = sysCompanyDao.findNameByOwner(loginName);
        return sysCompany;
    }


    /**
     * 与数据库中的用户名比对，是否正确
     *
     * @param sysUser
     * @return
     */
    @Override
    public SysUser selectLoginName(SysUser sysUser) {
        if (StringUtils.isNotBlank(sysUser.getLoginName())) {
            SysUser user = dao.selectLoginName(sysUser);
            return user;
        }
        return null;
    }

    /**
     * 与数据库中的密码比对，是否正确
     *
     * @param sysUser
     * @return
     */
    @Override
    public SysUser selectPassword(SysUser sysUser) {
        if (StringUtils.isNotBlank(sysUser.getPassword())) {
            SysUser user = dao.selectPasswrod(sysUser);
            return user;
        }
        return null;
    }
}
