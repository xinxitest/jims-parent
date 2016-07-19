package com.jims.common.utils;

import com.jims.common.vo.LoginInfo;
import com.jims.oauth2.integration.utils.Cache;
import com.jims.oauth2.integration.utils.CacheManager;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录信息
 */
public class LoginInfoUtils {
    public static LoginInfo getPersionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cache cache =CacheManager.getCacheInfo(session.getId());
        LoginInfo loginInfo =(LoginInfo)cache.getValue();
        return loginInfo;
    }

}
