
package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import com.jims.sys.dao.ServiceVsMenuDao;
import com.jims.sys.dao.SysServiceDao;
import com.jims.sys.dao.SysServicePriceDao;
import com.jims.sys.entity.ServiceVsMenu;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysServicePrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 系统服务BAO层
 *
 * @author txb
 * @version 2016-05-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SysServiceBo extends CrudImplService<SysServiceDao, SysService>{

    @Autowired
    private SysServicePriceDao priceDao;
    @Autowired
    private ServiceVsMenuDao serviceVsMenuDao;

    /**
     * 查询服务明细全部列表
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public List<SysServicePrice> findDetailList(String serviceId){
        return priceDao.findListByServiceId(serviceId);
    };


    /**
     * 修改保存删除服务明细
     * @param priceBeanVo
     * @return
     * @author txb
     * @version 2016-06-01
     */
    @Transactional(readOnly = false)
    public String saveDetail(DrugCatalogChangeVo priceBeanVo){
        if (priceBeanVo != null){
            List<SysServicePrice> inserts = priceBeanVo.getInserted();
            List<SysServicePrice> updates = priceBeanVo.getUpdated();
            List<SysServicePrice> deletes = priceBeanVo.getDeleted();

            if (inserts != null && inserts.size() > 0) {
                for (SysServicePrice sysServicePrice : inserts) {
                    sysServicePrice.preInsert();
                    priceDao.insert(sysServicePrice);

                }
            }
            if (updates != null && updates.size() > 0) {
                for (SysServicePrice sysServicePrice : updates) {
                    sysServicePrice.preUpdate();
                    priceDao.update(sysServicePrice);
                }
            }
            if (deletes != null && deletes.size() > 0) {
                for (SysServicePrice sysServicePrice : deletes) {
                    priceDao.delete(sysServicePrice);
                }
            }
        }
        return "1";
    }
    /**
     * 通过服务类别类型查询服务列表
     * @param serviceType 服务类型
     * @param serviceClass 服务类别
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public List<SysService> serviceListByTC( String serviceType,String serviceClass) {
        return dao.serviceListByTC(serviceType,serviceClass);
    }

    /**
     * 查询服务全部菜单
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public List<ServiceVsMenu> serviceVsMenuList(String serviceId){
        return serviceVsMenuDao.serviceVsMenuList(serviceId);
    }
    /**
     * 修改保存服务菜单
     * @param serviceVsMenus
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public String saveServiceVsMenu(List<ServiceVsMenu> serviceVsMenus){
        serviceVsMenuDao.deleteByServiceId(serviceVsMenus.get(0).getServiceId());
        for (ServiceVsMenu serviceVsMenu : serviceVsMenus){
                serviceVsMenu.preInsert();
                serviceVsMenuDao.insert(serviceVsMenu);
        }
        return "1";
    };



    /**
     * 检索不同人群的服务
     * @param serviceClass 服务人群 1,个人服务，0机构服务
     * @param serviceType  服务类型
     * @return
     */
    public List<SysService> findServiceWithPrice(String serviceClass,String serviceType){
        return dao.findServiceWithPrice(serviceClass,serviceType);
    }
}