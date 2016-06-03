package com.jims.clinic.api;

import com.jims.clinic.entity.PatHospitalNotice;
import com.jims.common.persistence.Page;

/**
 * Created by qinlongxin on 2016/6/1.
 */
public interface PatHospitalNoticeServiceApi {
    /**
     * 获取住院通知单信息
     * @param id
     *  @author qinlongxin
     * @return
     */
    public PatHospitalNotice get(String id);
    /**
     * 住院通知单分页
     * @param page,patHospitalNotice
     *  @author qinlongxin
     * @return
     */
    public Page<PatHospitalNotice> findPage(Page<PatHospitalNotice> page,PatHospitalNotice patHospitalNotice);
    /**
     * 删除住院通知单
     * @param ids 主键id集合
     *  @author qinlongxin
     * @return
     */
    public String delete(String ids);
    /**
     * 保存住院通知单
     * @param patHospitalNotice 实体类型
     *  @author qinlongxin
     * @return
     */
    public String save(PatHospitalNotice patHospitalNotice);
}
