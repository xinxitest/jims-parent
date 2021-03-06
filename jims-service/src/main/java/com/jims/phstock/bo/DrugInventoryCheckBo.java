package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.UserUtils;
import com.jims.phstock.dao.DrugInventoryCheckDao;
import com.jims.phstock.dao.DrugStockDao;
import com.jims.phstock.dao.DrugSubStorageDeptDao;
import com.jims.phstock.entity.*;
import com.jims.phstock.service.DrugExportService;
import com.jims.phstock.service.DrugImportDetailService;
import com.jims.phstock.vo.DrugInventoryCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lgx on 2016/6/17.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugInventoryCheckBo extends CrudImplService<DrugInventoryCheckDao, DrugInventoryCheck> {
    @Autowired
    private DrugInventoryCheckDao drugInventoryCheckDao;
    @Autowired
    private DrugSubStorageDeptDao drugSubStorageDeptDao;
    @Autowired
    private DrugStockDao drugStockDao;
    @Autowired
    private DrugImportDetailService drugImportDetailService;
    @Autowired
    private DrugExportService drugExportDetailService;

    /**
     * 生成盘点数据
     * @param storage 库房
     * @param orgId 组织机构
     * @param checkYearMonth 盘点日期
     * @return
     * @author txb
     */
    public List<DrugInventoryCheckVo> generateInventory(String storage, String orgId, String checkYearMonth,String subStorage) {
        return dao.generateInventory(storage,orgId,checkYearMonth,subStorage);
    }
    /**
     * 提取盘点数据
     * @param storage 库房
     * @param orgId 组织机构
     * @param checkYearMonth 盘点日期
     * @return
     * @author txb
     */
    public List<DrugInventoryCheckVo> extractInventory(String storage, String orgId, String checkYearMonth,String subStorage) {
        return dao.extractInventory(storage, orgId, checkYearMonth,subStorage);
    }
    /**
     * 暂存盘点数据
     * @param drugInventoryCheckVos
     * @return
     * @author txb
     */
    public void temporaryStorage(List<DrugInventoryCheckVo> drugInventoryCheckVos) {
        for (DrugInventoryCheckVo drugInventoryCheckVo : drugInventoryCheckVos){
            DrugInventoryCheck drugInventoryCheck = new DrugInventoryCheck();
            drugInventoryCheck =  generateInventoryByVo(drugInventoryCheck,drugInventoryCheckVo);
            drugInventoryCheck.setRecStatus(0);//暂存状态
            String id=drugInventoryCheckVo.getId();
            if(id==null ||""==id){
                drugInventoryCheck.preInsert();
                drugInventoryCheckDao.insert(drugInventoryCheck);
            }else{
                drugInventoryCheck.preUpdate();
                drugInventoryCheckDao.update(drugInventoryCheck);
            }


        }
    }
    /**
     * 保存盘点数据
     * @param drugInventoryCheckVos
     * @return
     * @author txb
     */
    public void saveInventory(List<DrugInventoryCheckVo> drugInventoryCheckVos) {
        int j = 0; //判断是终存标志

        for (DrugInventoryCheckVo drugInventoryCheckVo : drugInventoryCheckVos) {

            DrugInventoryCheck drugInventoryCheck = new DrugInventoryCheck();
            drugInventoryCheck = generateInventoryByVo(drugInventoryCheck, drugInventoryCheckVo);
            String id=drugInventoryCheckVo.getId();
            if(id==null ||""==id){   //判断暂存状态
                j=0;
                drugInventoryCheck.setRecStatus(1);
                drugInventoryCheck.preInsert();
                drugInventoryCheckDao.insert(drugInventoryCheck);
            }else{
                if(drugInventoryCheck.getRecStatus()==0){
                    j=0;
                    drugInventoryCheck.setRecStatus(1);
                    drugInventoryCheck.preUpdate();
                    drugInventoryCheckDao.update(drugInventoryCheck);
                }else{
                    j=1;
                }
            }
//                j=0;
//                drugInventoryCheck.preInsert();
//                drugInventoryCheckDao.insert(drugInventoryCheck);
//            }else if(drugInventoryCheck.getRecStatus()==0){
//
//            }else if (drugInventoryCheck.getRecStatus()==1){
//
//            }

//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            String checkYearMonth = simpleDateFormat.format(drugInventoryCheck.getCheckYearMonth());
//
//            List<DrugInventoryCheckVo> list = null;
//            if (i == 0) {
//                list = bo.extractInventory(drugInventoryCheck.getStorage(), drugInventoryCheck.getOrgId(), checkYearMonth.substring(0, 10), drugInventoryCheck.getSubStorage());
//                if (list.size() != 0) {
//                    i = 1;
//                }
//            }
//
//            if (i == 1) {
//                drugInventoryCheck.preUpdate();
//                bo.update(drugInventoryCheck);
//            } else {
//                drugInventoryCheck.preInsert();
//                bo.insert(drugInventoryCheck);
//            }
            //盈亏入出库
            if(j==0){
                Double accountQuantity = drugInventoryCheck.getAccountQuantity();//账面数
                Double actualQuantity = drugInventoryCheck.getActualQuantity();//实盘数

                String storage = drugInventoryCheck.getStorage();
                String subStorage = drugInventoryCheck.getSubStorage();

                String drugCode = drugInventoryCheck.getDrugCode();
                String drugSpec = drugInventoryCheck.getMinSpec();
                String firmId = drugInventoryCheck.getFirmId();
                String packageSpec = drugInventoryCheck.getDrugSpec();
                String batchNo = drugInventoryCheck.getBatchNo();
                String orgId = drugInventoryCheck.getOrgId();

                DrugSubStorageDept drugSubStorageDept = drugSubStorageDeptDao.findByUnique(storage, subStorage, orgId).get(0);
                Double num = Math.abs(accountQuantity - actualQuantity);//盘盈盘亏数

                DrugStock drugStock = drugStockDao.findByUnique(drugCode, drugSpec, firmId, packageSpec, batchNo, storage, subStorage, orgId).get(0);

                if (accountQuantity > actualQuantity) {
                    //盘亏出库

                    //出库明细记录
                    DrugExportDetail drugExportDetail = new DrugExportDetail();
                    drugExportDetail.setDocumentNo(generateDocumentNo(drugSubStorageDept.getImportNoPrefix(), drugSubStorageDept.getImportNoAva()));
                    drugExportDetail.setOrgId(drugStock.getOrgId());
                    drugExportDetail.setDrugCode(drugStock.getDrugCode());
                    drugExportDetail.setDrugSpec(drugStock.getDrugSpec());
                    drugExportDetail.setFirmId(drugStock.getFirmId());
                    drugExportDetail.setBatchNo(drugStock.getBatchNo());
                    drugExportDetail.setPackageSpec(drugStock.getPackageSpec());
                    drugExportDetail.setPackageUnits(drugStock.getPackageUnits());
                    drugExportDetail.setUnits(drugStock.getUnits());
                    drugExportDetail.setExpireDate(drugStock.getExpireDate());
                    drugExportDetail.setTradePrice(drugInventoryCheck.getTradePrice());
                    drugExportDetail.setRetailPrice(drugInventoryCheck.getRetailPrice());
                    drugExportDetail.setPurchasePrice(drugStock.getPurchasePrice());
                    drugExportDetail.setSubPackage1(drugStock.getSubPackage1());
                    drugExportDetail.setSubPackageUnits1(drugStock.getSubPackageUnits1());
                    drugExportDetail.setSubPackageSpec1(drugStock.getSubPackageSpec1());
                    drugExportDetail.setSubPackage2(drugStock.getSubPackage2());
                    drugExportDetail.setSubPackageUnits2(drugStock.getSubPackageUnits2());
                    drugExportDetail.setSubPackageSpec2(drugStock.getSubPackageSpec2());
                    drugExportDetail.setItemNo(1);
                    drugExportDetail.setQuantity(num);
                    drugExportDetail.setInventory(drugInventoryCheck.getActualQuantity());

                    drugExportDetail.setDrugStockId(drugStock.getId());

                    //出库单据
                    DrugExportMaster drugExportMaster = new DrugExportMaster();
                    drugExportMaster.setDocumentNo(generateDocumentNo(drugSubStorageDept.getImportNoPrefix(), drugSubStorageDept.getImportNoAva()));
                    drugExportMaster.setStorage(drugStock.getStorage());
                    drugExportMaster.setSubStorage(drugStock.getSubStorage());
                    drugExportMaster.setExportDate(new Date());
                    drugExportMaster.setOrgId(drugStock.getOrgId());
                    drugExportMaster.setAccountReceivable(drugInventoryCheck.getRetailPrice() * num);
                    drugExportMaster.setAccountPayed(drugInventoryCheck.getRetailPrice() * num);
                    drugExportMaster.setAdditionalFee(0.00);
                    drugExportMaster.setExportClass("盘点出库");
                    drugExportMaster.setAccountIndicator(1);
                    drugExportMaster.setOperator(UserUtils.getUser().getName());
                    drugExportMaster.setReceiver(drugStock.getStorage());

                    //调用函数数据准备
                    List<DrugExportDetail> drugExportDetails = new ArrayList<DrugExportDetail>();
                    drugExportDetails.add(drugExportDetail);
                    drugExportMaster.setDetailList(drugExportDetails);
                    drugExportMaster.setSubStorageDeptId(drugSubStorageDept.getId());
                    drugExportDetailService.saveMasterAndDetail(drugExportMaster);

                    //更新更改库存标志
                    drugInventoryCheck.setChangeFlag(1);
                    drugInventoryCheck.preUpdate();
                    dao.update(drugInventoryCheck);


                }
                if (accountQuantity < actualQuantity) {
                    //盘盈入库

                    //入库明细记录
                    DrugImportDetail drugImportDetail = new DrugImportDetail();

                    drugImportDetail.setDocumentNo(generateDocumentNo(drugSubStorageDept.getImportNoPrefix(), drugSubStorageDept.getImportNoAva()));
                    drugImportDetail.setOrgId(drugStock.getOrgId());
                    drugImportDetail.setDrugCode(drugStock.getDrugCode());
                    drugImportDetail.setDrugSpec(drugStock.getDrugSpec());
                    drugImportDetail.setFirmId(drugStock.getFirmId());
                    drugImportDetail.setBatchNo(drugStock.getBatchNo());
                    drugImportDetail.setPackageSpec(drugStock.getPackageSpec());
                    drugImportDetail.setPackageUnits(drugStock.getPackageUnits());
                    drugImportDetail.setUnits(drugStock.getUnits());
                    drugImportDetail.setExpireDate(drugStock.getExpireDate());
                    drugImportDetail.setDiscount(drugStock.getDiscount());
                    drugImportDetail.setTradePrice(drugInventoryCheck.getTradePrice());
                    drugImportDetail.setRetailPrice(drugInventoryCheck.getRetailPrice());
                    drugImportDetail.setPurchasePrice(drugStock.getPurchasePrice());
                    drugImportDetail.setSubPackage1(drugStock.getSubPackage1());
                    drugImportDetail.setSubPackageUnits1(drugStock.getSubPackageUnits1());
                    drugImportDetail.setSubPackageSpec1(drugStock.getSubPackageSpec1());
                    drugImportDetail.setSubPackage2(drugStock.getSubPackage2());
                    drugImportDetail.setSubPackageUnits2(drugStock.getSubPackageUnits2());
                    drugImportDetail.setSubPackageSpec2(drugStock.getSubPackageSpec2());
                    drugImportDetail.setItemNo(1);
                    drugImportDetail.setQuantity(num);
                    drugImportDetail.setInventory(drugInventoryCheck.getActualQuantity());
                    drugImportDetail.setInvoiceDate(null);
                    drugImportDetail.setInvoiceNo(null);

                    //入库单记录
                    DrugImportMaster drugImportMaster = new DrugImportMaster();
                    drugImportMaster.setDocumentNo(generateDocumentNo(drugSubStorageDept.getImportNoPrefix(), drugSubStorageDept.getImportNoAva()));
                    drugImportMaster.setStorage(drugStock.getStorage());
                    drugImportMaster.setSubStorage(drugStock.getSubStorage());
                    drugImportMaster.setImportDate(new Date());
                    drugImportMaster.setOrgId(drugStock.getOrgId());
                    drugImportMaster.setSupplier(drugStock.getStorage());
                    drugImportMaster.setAccountReceivable(drugInventoryCheck.getRetailPrice() * num);
                    drugImportMaster.setAccountPayed(drugInventoryCheck.getRetailPrice() * num);
                    drugImportMaster.setAdditionalFee(0.00);
                    drugImportMaster.setImportClass("盘点入库");
                    drugImportMaster.setAccountIndicator(1);
                    drugImportMaster.setOperator(UserUtils.getUser().getName());

                    //调用函数数据准备
                    List<DrugImportDetail> detailList = new ArrayList<DrugImportDetail>();
                    detailList.add(drugImportDetail);
                    drugImportMaster.setDetailList(detailList);
                    drugImportMaster.setSubStorageDeptId(drugSubStorageDept.getId());

                    drugImportDetailService.saveMasterAndDetail(drugImportMaster);
                    //更新更改库存标志
                    drugInventoryCheck.setChangeFlag(1);
                    drugInventoryCheck.preUpdate();
                    drugInventoryCheckDao.update(drugInventoryCheck);
                }
            }
        }
    }

    /**
     * 单号生成
     * @param noPrefix 前缀
     * @param noAva 后缀
     * @return
     * @author txb
     */
    private String generateDocumentNo(String noPrefix,Integer noAva){
        int num = 10;
        int noPrefixLength = noPrefix.length();
        int noAvaLength = noAva.toString().length();
        String zero = "";

        for (int i = 0;i<num-noAvaLength-noPrefixLength;i++){
            zero +="0";
        }

        return noPrefix + zero + noAva;
    }

    /**
     * 通过vo装换成实体
     * @param drugInventoryCheckVo
     * @return
     */
    private DrugInventoryCheck generateInventoryByVo(DrugInventoryCheck drugInventoryCheck,DrugInventoryCheckVo drugInventoryCheckVo){
        drugInventoryCheck.setRetailPrice(Double.parseDouble(drugInventoryCheckVo.getRetailPrice()));
        drugInventoryCheck.setTradePrice(Double.parseDouble(drugInventoryCheckVo.getTraderPrice()));
        drugInventoryCheck.setAccountQuantity(Double.parseDouble(drugInventoryCheckVo.getQuantity()));
        drugInventoryCheck.setActualQuantity(Double.parseDouble(drugInventoryCheckVo.getActualQuantity()));
        drugInventoryCheck.setStorage(drugInventoryCheckVo.getStorage());
        drugInventoryCheck.setSubStorage(drugInventoryCheckVo.getSubStorage());
        drugInventoryCheck.setBatchNo(drugInventoryCheckVo.getBatchNo());
        drugInventoryCheck.setRecStatus(Integer.parseInt(drugInventoryCheckVo.getRecStatus()));
        drugInventoryCheck.setChangeFlag(Integer.parseInt(drugInventoryCheckVo.getChangeFlag()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            drugInventoryCheck.setCheckYearMonth(simpleDateFormat.parse(drugInventoryCheckVo.getCheckYearMonth()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        drugInventoryCheck.setDrugCode(drugInventoryCheckVo.getDrugCode());
        drugInventoryCheck.setDrugSpec(drugInventoryCheckVo.getPackageSpec());
        drugInventoryCheck.setFirmId(drugInventoryCheckVo.getFirmId());
        drugInventoryCheck.setMinSpec(drugInventoryCheckVo.getDrugSpec());
        drugInventoryCheck.setMinUnits(drugInventoryCheckVo.getUnits());
        drugInventoryCheck.setUnits(drugInventoryCheckVo.getPackageUnits());
        drugInventoryCheck.setOrgId(drugInventoryCheckVo.getOrgId());
        drugInventoryCheck.setId(drugInventoryCheckVo.getId());

        return drugInventoryCheck;
    }
}
