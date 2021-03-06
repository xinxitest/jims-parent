package com.jims.asepsis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.asepsis.api.AsepsisAntiRecApi;
import com.jims.asepsis.api.AsepsisSendRecApi;
import com.jims.asepsis.api.AsepsisStockApi;
import com.jims.asepsis.entity.AsepsisAntiRec;
import com.jims.asepsis.entity.AsepsisSendRec;
import com.jims.asepsis.entity.AsepsisStock;
import com.jims.asepsis.vo.AsepsisAntiRecVo;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Auser on 2016/6/27.
 * Created by louhuili
 * 消毒包处理
 */
@Component
//@Produces("application/json")
@Produces(MediaType.APPLICATION_JSON)
@Path("asepsisAntiRec")
public class AsepsisAntiRecRest {

    @Reference(version = "1.0.0")
    private AsepsisAntiRecApi asepsisAntiRecApi;//消毒包API
    private AsepsisStockApi asepsisStockApi;//消毒包API


    /**
     * 查询对应状态下的消毒包
     * @param state 消毒包状态：0-未清洗；1-清洗未打包；2-打包未消毒；3-消毒加库存；null-未作任何处理
     * @return java.util.List<com.jims.prescription.entity.OutpPresc>    返回类型
     * @Title: list
     * @Description: (根据消毒包状态获取某状态下的消毒包)
     * @author LHL
     * @date 2016/6/27
     */
    @GET
    @Path("list")
    public List<AsepsisAntiRec> list(@QueryParam("state") String state,@QueryParam("orgId") String orgId,@QueryParam("belongDept") String belongDept,@QueryParam("asepsisName") String asepsisName){
        List<AsepsisAntiRec> list = Lists.newArrayList();
        try {
            AsepsisAntiRec aar = new AsepsisAntiRec();
            aar.setAsepsisState(state);
            aar.setOrgId(orgId);
            aar.setBelongDept((belongDept!=null&&!belongDept.equals(""))?belongDept:"");
            aar.setAsepsisName((asepsisName != null && !asepsisName.equals("")) ? asepsisName : "");
            list = asepsisAntiRecApi.getAsepsisAntiRecByState(aar);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 无菌物品处理（清洗）
     * @param asepsisAntiRecVo
     * @author lhl
     * @return
     */
    @Path("saveClean")
    @POST
    public StringData saveClean(AsepsisAntiRecVo<AsepsisAntiRec> asepsisAntiRecVo){
        List<AsepsisAntiRec> updated = asepsisAntiRecVo.getUpdated();
        int num = 0;
        String antiBatchNo = "";//消毒批号，在消毒(灭菌)时生成，同一批消毒的消毒批号相同
        //更新
        for (AsepsisAntiRec asepsisAntiRec : updated) {
            asepsisAntiRec.preUpdate();
            //消毒包处理均需要用到修改asepsis_Anti_Rec表,在bo层处理具体的过程：
            //当是清洗或打包时，只需要修改asepsis_Anti_Rec表，当是灭菌时，需要在根据灭菌数量判断是否需要在asepsis_Anti_Rec表再添加一条数据
            if(asepsisAntiRec.getAmountAnti()!=null&&!asepsisAntiRec.getAmountAnti().equals("0")&&!asepsisAntiRec.getAmountAnti().equals("0.0")&&!asepsisAntiRec.getAmountAnti().equals("0.00")
                    &&asepsisAntiRec.getAntiOperator()!=null&&!asepsisAntiRec.getAntiOperator().equals("")&&asepsisAntiRec.getAntiWays()!=null&&!asepsisAntiRec.getAntiWays().equals("")) {
                if(antiBatchNo.equals("")){
                    antiBatchNo = asepsisStockApi.getNextDocumentNo(asepsisAntiRec.getOrgId());
                    if(antiBatchNo==null||antiBatchNo.equals("")){
                        antiBatchNo = (new SimpleDateFormat("yyMMdd")).format(new Date())+"0001";
                    }
                }
                asepsisAntiRec.setAntiBatchNo(antiBatchNo);
            }
            num +=  asepsisAntiRecApi.saveClean(asepsisAntiRec);
        }
        StringData stringData = new StringData();
        String code = num + "";
        stringData.setCode(code);
        if(!"0".equals(code)&& !"".equals(code)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }
        return stringData;
    }





















//    /**
//     * @return java.util.List<com.jims.prescription.entity.OutpPresc>    返回类型
//     * @throws
//     * @Title: list
//     * @Description: (查询患者处方用药列表数据)
//     * @author CTQ
//     * @date 2016/4/23
//     */
//    @Path("sublist")
//    @GET
//    public List<OutpPresc> sublist(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("orgId") String orgId,@QueryParam("clinicId") String clinicId,@QueryParam("prescNo") Integer prescNo){
//        OutpPresc op = new OutpPresc();
//        op.setPrescNo(prescNo);
//        op.setOrgId(orgId);
//        op.setClinicId(clinicId);
//        List<OutpPresc> list = Lists.newArrayList();
//        try {
//            list = outpPrescServiceApi.findListByParams(op);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    /**
//     * @param          outpPresc   传递参数
//     * @return com.jims.common.data.StringData    返回类型
//     * @throws
//     * @Title: save
//     * @Description: (保存处方用药信息)
//     * @author CTQ
//     * @date 15:18
//     */
//    @Path("save")
//    @POST
//    public StringData save(OutpPresc outpPresc){
//        StringData stringData=new StringData();
//        try {
//            String data = outpPrescServiceApi.save(outpPresc);
//            stringData.setCode(data);
//            stringData.setData(data.compareTo("0") > 0 ? "success":"error");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return stringData;
//    }
//
//    /**
//     * @param ids 传递参数
//     * @return StringData    返回类型
//     * @throws
//     * @Title: delete
//     * @Desription: (处方删除药品,同时删除医嘱及计价项目)
//     * @author CTQ
//     * @date 2016年4月23日15:11:52
//     */
//    @Path("delete")
//    @POST
//    public StringData delete(String ids){
//        StringData stringData=new StringData();
//        String num=outpPrescServiceApi.deletePresc(ids);
//        stringData.setCode(num);
//        stringData.setData("success");
//        return stringData;
//    }
//    /**
//     * @param       id      传递参数
//     * @return com.jims.clinic.entity.ClinicMaster    返回类型
//     * @throws
//     * @Title: getClinicMaster
//     * @Description: (点击新方，获取当前病人的信息)
//     * @author CTQ
//     * @date 2016/5/12
//     */
//    @Path("getClinicMaster")
//    @POST
//    public ClinicMaster getClinicMaster(String id) {
//        ClinicMaster clinicMaster = clinicMasterServiceApi.get(id);
//        return clinicMaster;
//    }
//
//
//    @Path("dictlist")
//    @GET
//    public List<OutpPresc> dictlist(){
//        List<OutpPresc> list = Lists.newArrayList();
//        OutpPresc dict = new OutpPresc();
//        dict.setDrugCode("1");
//        dict.setDrugName("阿莫西林");
//        dict.setDrugSpec("10g*2");
//        dict.setFirmId("YS000023");
//        dict.setDosage(Double.valueOf(1));
//        dict.setDosageUnits("片");
//        dict.setItemClass("A");
//        list.add(dict);
//        return list;
//    }
//
//    @Path("priceItem")
//    @GET
//    public List<OutpOrdersCosts> priceItem(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("masterId") String masterId,@QueryParam("clinicId") String clinicId){
//        List<OutpOrdersCosts> list = null;
//        try {
//            list = Lists.newArrayList();
//            list = outpOrdersCostsServiceApi.getOutpCosts(masterId,clinicId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
}

