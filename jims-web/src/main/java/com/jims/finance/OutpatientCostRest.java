package com.jims.finance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.BaseData;
import com.jims.common.data.StringData;
import com.jims.common.utils.StringUtils;
import com.jims.common.web.impl.BaseDto;
import com.jims.finance.api.OutPatientCostServiceApi;
import com.jims.finance.entity.OutpBillItems;
import com.jims.finance.entity.OutpRcptMaster;
import com.jims.patient.entity.PatMasterIndex;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 * 划价收费
 * @author zhangyao
 */
@Component
@Produces("application/json")
@Path("outPatientCost")
public class OutpatientCostRest {
    @Reference(version = "1.0.0")
    private OutPatientCostServiceApi outPatientCostApi;


    /**
     * 根据门诊号查询处方，处置治疗信息
     * @return
     */
    @Path("list")
    @GET
    public BaseData<BaseDto>  getPatientList(@QueryParam("clinicNo") String clinicNo,@QueryParam("orgId") String orgId){
        BaseData<BaseDto> baseData=outPatientCostApi.list(orgId, clinicNo);
        return baseData;
    }

    /**
     * 确认收费
     * @param ids
     * @return
     */
    @Path("confirmPay")
    @POST
    public StringData confirmPay(String ids){
        StringData data =new StringData();
        return data;
    }

    /**
     * 根据门诊号，获取退费信息
     * @param clinicNo
     * @param orgId
     * @return
     */
    @Path("getBackChargeInfo")
    @GET
    public List<OutpRcptMaster> getBackChargeInfo(@QueryParam("clinicNo")String clinicNo,@QueryParam("orgId")String orgId){
         List<OutpRcptMaster> list=outPatientCostApi.getBackChargeInfo(clinicNo,orgId);
        return list;
    }

    /**
     * 根据收据号加载 费用项目
     * @param rcptNO
     * @return
     * @author zhaoning
     */
    @Path("getBackChargeItems")
    @GET
    public List<OutpBillItems> getBackChargeItems(@QueryParam("rcptNO")String rcptNO){
        List<OutpBillItems> outpBillItemses=outPatientCostApi.getBackChargeItems(rcptNO);
        return  outpBillItemses;
    }
}
