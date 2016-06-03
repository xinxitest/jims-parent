package com.jims.clinic.api;

import com.jims.clinic.entity.ClinicMaster;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/4/22.
 * 病人就诊记录Api
 *@author zhaonig
 * @version 2016-04-22
 */
public interface ClinicMasterServiceApi {

    /**
     * 获取就诊记录信息
     * @param id
     * @return
     */
    public ClinicMaster get(String id);

    /**
     * 根据当前登录人 查询 病人列表(待诊病人)
     * @param doctorID
     * @return
     */
    public List<ClinicMaster> getClinicMasterList(String doctorID);

    /**
     * 根据当前登录人 查询  病人列表（已诊病人）
     * @param doctorID
     * @return
     */
    public List<ClinicMaster> getClinicMasterDiagnosed(String doctorID);

    /**
     * 根据参数查询挂号数据
     * @param operator
     * @param date
     * @author CTQ
     * @return
     */
    public ClinicMaster findFeeForm(String operator,Date date);
}
