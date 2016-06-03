package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.*;
import com.jims.common.data.BaseData;
import com.jims.common.utils.StringUtils;
import com.jims.common.web.impl.BaseDto;
import com.jims.finance.api.OutPatientCostServiceApi;
import com.jims.finance.dao.OutpBillItemsDao;
import com.jims.finance.dao.OutpRcptMasterDao;
import com.jims.finance.entity.OutpBillItems;
import com.jims.finance.entity.OutpRcptMaster;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * 划价收费
 *
 * @author zhangyao
 * @date2016/5/25
 */
@Service(version = "1.0.0")
public class OutPatientCostServiceImpl  implements OutPatientCostServiceApi {


    @Autowired
    private ClinicMasterDao clinicMasterDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;
    @Autowired
    private OutpRcptMasterDao outpRcptMasterDao;
    @Autowired
    private OutpBillItemsDao outpBillItemsDao;


    public BaseData<BaseDto>  list(String orgId, String clinicNo){
        BaseData<BaseDto> baseData=new BaseData<BaseDto>();
        if(StringUtils.isEmpty(orgId)){
            baseData.setCode("组织机构ID不能为空");
            return baseData;
        }
        if(StringUtils.isEmpty(clinicNo)){
            baseData.setCode("门诊号不能为空");
            return baseData;
        }
        BaseDto b=clinicMasterDao.getClinicMasterCost(orgId,clinicNo);
        List<BaseDto> list = null;
        if(b==null){
            baseData.setCode("没有对应的病人");
            baseData.setDatas(list);
            return baseData;
        }
        baseData.setCode("success");
        list=clinicMasterDao.getClinicMasterCostAll(b.getAsString("id"));
        List<BaseDto> list1 =outpOrdersCostsDao.getCostAll(b.getAsString("id"));
        baseData.setDatas(list);
        baseData.setDatas1(list1);
        baseData.setData(b);
        return baseData;
    };

    /**
     * 确认收费信息
     * @param ids
     * @return
     * @author zhaoning
     */
    @Override
    public String confirmPay(String ids) {

        /*调用存储过程*/
        ids="6f09b8c7726c4a0db03063f6728c8f08,";
        outpOrdersCostsDao.confirmPay(ids);
        return null;
    }

    /**
     * 根据门诊号，获取，退费信息
     * @param clinicNo
     * @param orgId
     * @return
     * @author zhaoning
     */
    @Override
    public List<OutpRcptMaster> getBackChargeInfo(String clinicNo, String orgId) {
        return outpRcptMasterDao.getBackChargeInfo(clinicNo,orgId);
    }

    /**
     * 根据收据号查询 门诊收费项目
     * @param rcptNo
     * @return
     * @author zhaoning
     */
    @Override
    public List<OutpBillItems> getBackChargeItems(String rcptNo) {
        return outpBillItemsDao.getBackChargeItems(rcptNo) ;
    }
}
