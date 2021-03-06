package com.jims.sys.bo;

import com.jims.clinic.dao.ClinicItemDictDao;
import com.jims.clinic.dao.ClinicItemNameDictDao;
import com.jims.clinic.dao.ClinicVsChargeDao;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.clinic.entity.ClinicVsCharge;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.common.utils.IdGen;
import com.jims.sys.dao.PerformDeptDao;
import com.jims.sys.dao.PriceItemNameDictDao;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.entity.PerformDept;
import com.jims.sys.entity.PriceItemNameDict;
import com.jims.sys.entity.PriceList;
import com.jims.sys.vo.PriceDictListVo;
import com.jims.sys.vo.PriceListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * bo层
 * @author lgx
 * @version 2016-06-16
 */
@Service
@Component
@Transactional(readOnly = false)
public class PriceListBo extends CrudImplService<PriceListDao, PriceList> {
    @Autowired
    private PriceItemNameDictDao priceItemNameDictDao;  //价表项目名称
    @Autowired
    private PriceListDao priceListDao;      //价表
    @Autowired
    private PerformDeptDao performDeptDao;  //价表项目执行科室
    @Autowired
    private ClinicItemDictDao clinicItemDictDao;    //诊疗项目
    @Autowired
    private ClinicItemNameDictDao clinicItemNameDictDao;    //诊疗项目名称
    @Autowired
    private ClinicVsChargeDao clinicVsChargeDao;    //诊疗项目与价表对照

    /**
     * 价表的保存
     * @param dictListVo
     * @return
     * @author fengyuguang
     */
    public String saveData(PriceDictListVo dictListVo) {
        int code = 0;
        //保存价表项目名称
        PriceItemNameDict priceItemNameDict = new PriceItemNameDict();
        priceItemNameDict.setId(IdGen.uuid());
        priceItemNameDict.setItemClass(dictListVo.getItemClass());
        priceItemNameDict.setItemName(dictListVo.getItemName());
        priceItemNameDict.setItemCode(dictListVo.getItemCode());
        priceItemNameDict.setInputCode(dictListVo.getInputCode());
        priceItemNameDict.setMemo(dictListVo.getMemo());
        priceItemNameDict.setStdIndicator(1);   //正名
        priceItemNameDict.setOrgId(dictListVo.getOrgId());
        code = priceItemNameDictDao.insert(priceItemNameDict);//保存价表项目名称

        //保存价表
        PriceList priceList = new PriceList();
        priceList.setId(IdGen.uuid());
        priceList.setItemClass(dictListVo.getItemClass());
        priceList.setItemName(dictListVo.getItemName());
        priceList.setItemCode(dictListVo.getItemCode());
        priceList.setItemSpec(dictListVo.getItemSpec());
        priceList.setUnits(dictListVo.getUnits());
        priceList.setPrice(dictListVo.getPrice());
        priceList.setPreferPrice(dictListVo.getPreferPrice());
        priceList.setForeignerPrice(dictListVo.getForeignerPrice());
        priceList.setPerformedBy(dictListVo.getPerformedBy());
        priceList.setFeeTypeMask(dictListVo.getFeeTypeMask());
        priceList.setClassOnInpRcpt(dictListVo.getClassOnInpRcpt());
        priceList.setClassOnOutpRcpt(dictListVo.getClassOnOutpRcpt());
        priceList.setClassOnReckoning(dictListVo.getClassOnReckoning());
        priceList.setSubjCode(dictListVo.getSubjCode());
        priceList.setClassOnMr(dictListVo.getClassOnMr());
        priceList.setMemo(dictListVo.getMemo());
        priceList.setStartDate(DateUtils.parseDate(dictListVo.getStartDate()));
        priceList.setInputCode(dictListVo.getInputCode());
        priceList.setMaterialCode(dictListVo.getMaterialCode());
        priceList.setOrgId(dictListVo.getOrgId());
        code += priceListDao.insert(priceList);         //保存价表

        //保存执行科室表
        PerformDept performDept = new PerformDept();
        performDept.preInsert();    //主键
        performDept.setItemClass(priceList.getItemClass());
        performDept.setItemCode(priceList.getItemCode());
        performDept.setPerformedBy(priceList.getPerformedBy());
        performDept.setOrgId(priceList.getOrgId());
        code += performDeptDao.insert(performDept);     //保存价表项目执行科室

        //判断诊疗标识，如果是1则生成诊疗项目、诊疗项目名称、诊疗项目与价表对照
        if(dictListVo.getClinicDict().equals("1")){
            //保存诊疗项目
            ClinicItemDict clinicItem = new ClinicItemDict();
            clinicItem.setId(IdGen.uuid());
            clinicItem.setItemName(dictListVo.getItemName());
            clinicItem.setItemClass(dictListVo.getItemClass());
            clinicItem.setItemCode(dictListVo.getItemCode());
            clinicItem.setInputCode(dictListVo.getInputCode());
            clinicItem.setExpand3(dictListVo.getPerformedBy());
            clinicItem.setMemo(dictListVo.getMemo());
            clinicItem.setItemStatus("0");
            clinicItem.setOrgId(dictListVo.getOrgId());
            code += clinicItemDictDao.insert(clinicItem);     //保存诊疗项目

            //保存诊疗项目名称
            ClinicItemNameDict clinicItemName = new ClinicItemNameDict();
            clinicItemName.setId(IdGen.uuid());
            clinicItemName.setItemClass(dictListVo.getItemClass());
            clinicItemName.setItemCode(dictListVo.getItemCode());
            clinicItemName.setItemName(dictListVo.getItemName());
            clinicItemName.setInputCode(dictListVo.getInputCode());
            clinicItemName.setStdIndicator(1);  //正名
            clinicItemName.setExpand3(dictListVo.getPerformedBy());
            clinicItemName.setItemStatus("1");
            clinicItemName.setOrgId(dictListVo.getOrgId());
            code += clinicItemNameDictDao.insert(clinicItemName);   //保存诊疗项目名称

            //保存诊疗项目与价表对照
            ClinicVsCharge clinicVsCharge = new ClinicVsCharge();
            clinicVsCharge.setId(IdGen.uuid());
            clinicVsCharge.setClinicItemClass(dictListVo.getItemClass());
            clinicVsCharge.setClinicItemCode(dictListVo.getItemCode());
            clinicVsCharge.setChargeItemNo(1);
            clinicVsCharge.setChargeItemClass(dictListVo.getItemClass());
            clinicVsCharge.setChargeItemCode(dictListVo.getItemCode());
            clinicVsCharge.setChargeItemSpec(dictListVo.getItemSpec());
            clinicVsCharge.setAmount(1);
            clinicVsCharge.setUnits(dictListVo.getUnits());
            clinicVsCharge.setOrgId(dictListVo.getOrgId());
            code += clinicVsChargeDao.insert(clinicVsCharge);
        }
        return code + "";
    }

    /**
     * 修改价表
     * @param priceDictListVo
     * @return
     * @author fengyuguang
     */
    public String updatePrice(PriceDictListVo priceDictListVo) {
        int code = 0;
        //修改价表
        PriceList priceList = new PriceList();
        priceList.setId(priceDictListVo.getId());
        priceList.setItemClass(priceDictListVo.getItemClass());
        priceList.setItemName(priceDictListVo.getItemName());
        priceList.setItemCode(priceDictListVo.getItemCode());
        priceList.setItemSpec(priceDictListVo.getItemSpec());
        priceList.setUnits(priceDictListVo.getUnits());
        priceList.setPrice(priceDictListVo.getPrice());
        priceList.setPreferPrice(priceDictListVo.getPreferPrice());
        priceList.setForeignerPrice(priceDictListVo.getForeignerPrice());
        priceList.setPerformedBy(priceDictListVo.getPerformedBy());
        priceList.setFeeTypeMask(priceDictListVo.getFeeTypeMask());
        priceList.setClassOnInpRcpt(priceDictListVo.getClassOnInpRcpt());
        priceList.setClassOnOutpRcpt(priceDictListVo.getClassOnOutpRcpt());
        priceList.setClassOnReckoning(priceDictListVo.getClassOnReckoning());
        priceList.setSubjCode(priceDictListVo.getSubjCode());
        priceList.setClassOnMr(priceDictListVo.getClassOnMr());
        priceList.setMemo(priceDictListVo.getMemo());
        priceList.setStartDate(DateUtils.parseDate(priceDictListVo.getStartDate()));
        priceList.setStopDate(DateUtils.parseDate(priceDictListVo.getStopDate()));  //停用日期
        priceList.setInputCode(priceDictListVo.getInputCode());
        priceList.setMaterialCode(priceDictListVo.getMaterialCode());
        priceList.setOrgId(priceDictListVo.getOrgId());
        code += priceListDao.update(priceList);        //修改价表

        //根据组织机构ID和项目代码查询执行科室
        PerformDept dept = performDeptDao.getByOrgIdItemCode(priceDictListVo.getOrgId(), priceDictListVo.getItemCode());
        if(dept != null){
            dept.setOrgId(priceDictListVo.getOrgId());
            dept.setItemClass(priceDictListVo.getItemClass());
            dept.setItemCode(priceDictListVo.getItemCode());
            dept.setPerformedBy(priceDictListVo.getPerformedBy());
            code += performDeptDao.update(dept);    //修改项目执行科室
        }

        //查询是否有相关的诊疗项目
        ClinicItemDict clinicItemDict = clinicItemDictDao.findByOrgIdItemNameItemCode(priceDictListVo.getOrgId(), priceDictListVo
                .getItemName(), priceDictListVo.getItemCode());
        if(clinicItemDict != null){
            clinicItemDict.setItemName(priceDictListVo.getItemName());
            clinicItemDict.setItemClass(priceDictListVo.getItemClass());
            clinicItemDict.setItemCode(priceDictListVo.getItemCode());
            clinicItemDict.setInputCode(priceDictListVo.getInputCode());
            clinicItemDict.setExpand3(priceDictListVo.getPerformedBy());
            clinicItemDict.setMemo(priceDictListVo.getMemo());
            clinicItemDict.setItemStatus("0");
            clinicItemDict.setOrgId(priceDictListVo.getOrgId());
            code += clinicItemDictDao.update(clinicItemDict);   //修改诊疗项目
        }

        ClinicItemNameDict itemNameDict = clinicItemNameDictDao.findByOrgIdItemNameItemCode(priceDictListVo.getOrgId(),
                priceDictListVo
                .getItemName(), priceDictListVo.getItemCode());
        if(itemNameDict != null){
            itemNameDict.setItemClass(priceDictListVo.getItemClass());
            itemNameDict.setItemCode(priceDictListVo.getItemCode());
            itemNameDict.setItemName(priceDictListVo.getItemName());
            itemNameDict.setInputCode(priceDictListVo.getInputCode());
            itemNameDict.setStdIndicator(1);  //正名
            itemNameDict.setExpand3(priceDictListVo.getPerformedBy());
            itemNameDict.setItemStatus("1");
            itemNameDict.setOrgId(priceDictListVo.getOrgId());
            code += clinicItemNameDictDao.update(itemNameDict);     //修改诊疗项目名称
        }

        ClinicVsCharge clinicVsCharge = clinicVsChargeDao.findByOrgIdItemCode(priceDictListVo.getOrgId(), priceDictListVo.getItemCode());
        if(clinicVsCharge != null){
            clinicVsCharge.setClinicItemClass(priceDictListVo.getItemClass());
            clinicVsCharge.setClinicItemCode(priceDictListVo.getItemCode());
            clinicVsCharge.setChargeItemNo(1);
            clinicVsCharge.setChargeItemClass(priceDictListVo.getItemClass());
            clinicVsCharge.setChargeItemCode(priceDictListVo.getItemCode());
            clinicVsCharge.setChargeItemSpec(priceDictListVo.getItemSpec());
            clinicVsCharge.setAmount(1);
            clinicVsCharge.setUnits(priceDictListVo.getUnits());
            clinicVsCharge.setOrgId(priceDictListVo.getOrgId());
            code += clinicVsChargeDao.update(clinicVsCharge);   //修改诊疗项目与价表对照
        }
        return code + "";
    }

    /**
     * 保存调价
     * @param priceDictListVo
     * @return
     * @author fengyuguang
     */
    public String saveAdjustPrice(PriceDictListVo priceDictListVo) {
        int code = 0;
        //查询老的价表，设置停止日期为新的同名价表的开始日期
        PriceList oldPriceList = priceListDao.get(priceDictListVo.getId());
        if(oldPriceList != null){
            oldPriceList.setStopDate(DateUtils.parseDate(priceDictListVo.getStopDate()));
            code += priceListDao.update(oldPriceList);
        }
        //保存新的价表
        PriceList newPriceList = new PriceList();
        newPriceList.preInsert();
        newPriceList.setItemClass(priceDictListVo.getItemClass());
        newPriceList.setItemCode(priceDictListVo.getItemCode());
        newPriceList.setItemName(priceDictListVo.getItemName());
        newPriceList.setItemSpec(priceDictListVo.getItemSpec());
        newPriceList.setUnits(priceDictListVo.getUnits());
        newPriceList.setPrice(priceDictListVo.getPrice());
        newPriceList.setPreferPrice(priceDictListVo.getPreferPrice());
        newPriceList.setForeignerPrice(priceDictListVo.getForeignerPrice());
        newPriceList.setPerformedBy(priceDictListVo.getPerformedBy());
        newPriceList.setFeeTypeMask(priceDictListVo.getFeeTypeMask());
        newPriceList.setClassOnInpRcpt(priceDictListVo.getClassOnInpRcpt());
        newPriceList.setClassOnOutpRcpt(priceDictListVo.getClassOnOutpRcpt());
        newPriceList.setClassOnReckoning(priceDictListVo.getClassOnReckoning());
        newPriceList.setSubjCode(priceDictListVo.getSubjCode());
        newPriceList.setClassOnMr(priceDictListVo.getClassOnMr());
        newPriceList.setMemo(priceDictListVo.getMemo());
        newPriceList.setStartDate(DateUtils.parseDate(priceDictListVo.getStartDate()));
        newPriceList.setStopDate(null);
        newPriceList.setMaterialCode(priceDictListVo.getMaterialCode());
        newPriceList.setInputCode(priceDictListVo.getInputCode());
        newPriceList.setCreateDate(new Date());
        newPriceList.setDelFlag("0");
        newPriceList.setOrgId(priceDictListVo.getOrgId());
        code += priceListDao.insert(newPriceList);

        return code + "";
    }

    /**
     * 根据类别查询价表
     * @param itemClass 类别
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<PriceList> findByItemClass(String itemClass, String orgId) {
        return priceListDao.findByItemClass(itemClass, orgId);
    }

    /**
     * 根据输入码查询价表数据
     * @param inputCode 输入码
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<PriceList> getByInputCode(String inputCode, String orgId) {
        return priceListDao.getByInputCode(inputCode, orgId);
    }

    /**
     * 查询序列
     *
     * @return
     */
    public String findSeqences() {
        return priceListDao.findSeqences();
    }

    /**
     * 通过拼音码查询数据
     *
     * @param inputCode
     * @return
     */
    public List<PriceList> findCode(String inputCode){
        return  priceListDao.findCode(inputCode);
    }
    /**
     * 现行价格表
     * @param page
     * @param priceListVo
     * @return
     * @author wei
     */
    public Page<PriceListVo> findPage(String orgId,Page<PriceListVo> page, PriceListVo priceListVo) {
        priceListVo.setPage(page);
        page.setList(dao.findPriceList(orgId, priceListVo));
        return page;
    }

    /**
     * 历史价格表
     * @param page
     * @param priceListVo
     * @return
     * @author wei
     */
    public Page<PriceListVo> findOLdPage(String orgId,Page<PriceListVo> page, PriceListVo priceListVo) {
        priceListVo.setPage(page);
        page.setList(dao.findOLdPriceList(orgId,priceListVo));
        return page;
    }

    /**
     * 拼音码查询现行价表
     * @param inputCode
     * @param label
     * @return
     * @author wei
     */
    public List<PriceListVo> getInputCodeNow(String orgId,String inputCode,String label) {
        List<PriceListVo> list =dao.getInputCodeNow(orgId, inputCode, label);
        return list;
    }

    /**
     * 拼音码查询历史价表
     * @param orgId
     * @param inputCode
     * @param label
     * @return
     */
    public List<PriceListVo> getInputCodeOld(String orgId, String inputCode, String label) {
        List<PriceListVo> list =dao.getInputCodeOld(orgId, inputCode, label);
        return list;
    }

    /**
     * 下拉框查询药品类别
     * @return
     * @author wei
     */
    public List<PriceListVo> list() {
        return dao.list();
    }


    /**
     * 根据诊疗项目获取诊疗项目所对应的价表项目
     * @param orgId
     * @param clinicItemCode
     * @return
     */
    public List<PriceListVo> getListByClinicItemCodeAndOrgId(String orgId, String clinicItemCode) {
        return dao.listByClinicItemCodeAndOrgId(orgId,clinicItemCode);
    }

    /**
     * 调价通知单
     * @param label
     * @param startDate
     * @param stopDate
     * @param orgId
     * @return
     */
    public  List<PriceList> priceNotice(String label,String startDate,String stopDate,String orgId){
        return priceListDao.priceNotice(label,startDate,stopDate,orgId);
    };

}