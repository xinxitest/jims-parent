/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */

/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.useBlood.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.blood.api.BloodApplyServiceApi;
import com.jims.doctor.useBlood.bo.BloodApplyBo;
import com.jims.doctor.useBlood.bo.BloodApplyHosBo;
import com.jims.doctor.useBlood.dao.BloodApplylDao;
import com.jims.blood.entity.BloodApply;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用血申请Service
 *
 * @author qlx
 * @version 2016-04-28
 */
@Service(version = "1.0.0")

public class BloodApplyServiceImpl extends CrudImplService<BloodApplylDao, BloodApply> implements BloodApplyServiceApi {

    @Autowired
    private BloodApplyBo bloodApplyBo;
    @Autowired
    private BloodApplyHosBo bloodApplyHosBo;

    /**
     * 保存用血申请和用血量申请
     *
     * @author zp
     * @version 2016-6-28
     */

    public String saveBloodApply(BloodApply bloodApply) {
        return bloodApplyBo.saveBloodApply(bloodApply);
    }

    /**
     * 住院用血申请保存
     * @param bloodApply
     * @return
     */
    @Override
    public String saveHosBloodApply(BloodApply bloodApply) {
        String num = bloodApplyHosBo.saveHosBloodApply(bloodApply);
        return num;

    }

//    @Override
//    public String getMatchSubNum(String applyNum) {
//        return bloodApplyBo.getMatchSubNum(applyNum);
//    }
}