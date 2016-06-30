package com.jims.patient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.PatsInHospitalServiceApi;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.data.StringData;
import com.jims.finance.api.PrepaymentRcptServiceApi;
import com.jims.finance.entity.PrepaymentRcpt;
import com.jims.patient.api.PatMasterIndexServiceApi;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 病人主索引
 * @Created by CTQ
 * @DATE 2016/5/25.
 */
@Component
@Produces("application/json")
@Path("patMasterIndex")
public class PatMasterIndexRest {

    @Reference(version = "1.0.0")
    PatMasterIndexServiceApi patMasterIndexServiceApi;
    @Reference(version = "1.0.0")
    PrepaymentRcptServiceApi prepaymentRcptServiceApi;
    @Reference(version = "1.0.0")
    PatsInHospitalServiceApi patsInHospitalServiceApi;
    /**
     * @param        patientId     传递参数
     * @return java.util.List<com.jims.patient.entity.PatMasterIndex>    返回类型
     * @throws
     * @Title: getPatientList
     * @Description: (根据条件查询病人列表)
     * @author CTQ
     * @date 2016/5/25
     */
    @Path("list")
    @GET
    public List<PatMasterIndex> getPatientList(@QueryParam(value = "patientId")String patientId){
        PatMasterIndex patMasterIndex = new PatMasterIndex();
        List<PatMasterIndex> list = Lists.newArrayList();
        try {
            list = patMasterIndexServiceApi.findList(patMasterIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * @param         patMasterIndex    传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: save
     * @Description: (保存病人信息及关联数据信息)
     * @author CTQ
     * @date 2016/5/25
     */
    @Path("save")
    @POST
    public StringData save(PatMasterIndex patMasterIndex){
        StringData stringData=new StringData();
        try {
            String data = patMasterIndexServiceApi.saveMasterIndex(patMasterIndex);
            stringData.setCode(data);
            stringData.setData(data.compareTo("0") > 0 ? "success":"error");
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringData;
    }
    /**
     * @param       ids      传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: delete
     * @Description: (取消登记-删除)
     * @author CTQ
     * @date 2016/5/25
     */
    @Path("delete")
    @POST
    public StringData delete(String ids){
        StringData stringData=new StringData();
        String num = "";
        //判断是否缴纳预交金，如果没有，执行取消登记
        List<PrepaymentRcpt> list = Lists.newArrayList();
        if(ids!=null&&!"".equals(ids)){
            //判断选中病人是否是已入院病人
            PatsInHospital patsInHospital = patsInHospitalServiceApi.findByPatientId(ids);
            if(patsInHospital!=null){
                list =  prepaymentRcptServiceApi.findByPatientId(ids);
                if(list!=null&&list.size()>0){
                    stringData.setData("warning");
                }else{
                    num=patMasterIndexServiceApi.deleteMasterIndex(ids);
                    stringData.setCode(num);
                    stringData.setData(num.compareTo("0")>0?"success":"error");
                }
            }else {
                stringData.setData("warn");
            }

        }
        return stringData;
    }

}
