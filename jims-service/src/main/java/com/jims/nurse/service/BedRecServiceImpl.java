package com.jims.nurse.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.web.impl.BaseDto;
import com.jims.nurse.api.BedRecServiceApi;
import com.jims.nurse.bo.BedRecBo;
import com.jims.nurse.entity.BedRec;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * BedRecServiceImpl
 *
 * @author PangQian
 * @date2016/6/2 0002
 */
@Service(version = "1.0.0")
public class BedRecServiceImpl implements BedRecServiceApi {
  @Autowired
    private BedRecBo bedRecBo;
    /**
     * 查询护士所负责的病区的床位信息
     * @param bedRecPage
     * @param bedRec
     * @author pq
     * @return
     */
    public Page<BedRec> findPage(Page<BedRec> bedRecPage,BedRec bedRec){
       return bedRecBo.findPage(bedRecPage,bedRec);
    }

    /**
     * 保存床位信息
     * @param bedRecList
     * @author pq
     * @return
     */
    public String saveBed(List<BedRec> bedRecList){
    return  bedRecBo.saveBed(bedRecList);
    }

    /**
     * 判断 病区 下的床位号的唯一性
     * @param bedNo
     * @param wardCode
     * @author pq
     * @return
     */
    public boolean judgeBedNo(Integer bedNo,String wardCode){
      return bedRecBo.judgeBedNo(bedNo,wardCode);
    }

    /**
     * 删除床位信息
     * @param ids
     * @author pq
     * @return
     */
    public String delete(String ids){
     return bedRecBo.delete(ids);
    }


  /**
   * 查询病区下所有的床位信息
   * @param wardCode
   * @author pq
   * @return
   */
  public List<BaseDto> getAllBed(String wardCode){
    return  bedRecBo.getAllBed(wardCode);
  }
}
