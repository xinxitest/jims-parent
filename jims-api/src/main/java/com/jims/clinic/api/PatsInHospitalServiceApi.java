package com.jims.clinic.api;


import com.jims.clinic.entity.PatsInHospital;
import com.jims.clinic.vo.ComeDeptVo;
import com.jims.common.web.impl.BaseDto;

import java.util.List;

/**
 * @author CTQ
 * @date 2016-06-06 09:42:39
 * 病人在院记录
 */
public interface PatsInHospitalServiceApi {


    public String saveHospInfo(ComeDeptVo comeDeptVo);

    /**
     * 转科
     * @param comeDeptVo
     * @return
     */
    public String turnOutDept(ComeDeptVo comeDeptVo);

    /**
     * 查询病人是否已入院
     * @param patientId
     * @return
     */
    public PatsInHospital findByPatientId(String patientId);

    /**
     * 出院-根据床位和病区查询病人信息
     * @param bedNo
     * @param wardCode
     * @author CTQ
     * @return
     */
    public BaseDto searchInfoByParams(Integer bedNo,String wardCode);
    /**
     * 转出-根据床位和病区查询病人信息
     * @param bedNo
     * @param wardCode
     * @author CTQ
     * @return
     */
    public BaseDto searchTurnOutInfoByParams(Integer bedNo,String wardCode);
    /**
     * 取消转出-待专科病人列表
     * @author CTQ
     * @return
     */
    public List<BaseDto> waitTurnOutList();
    /**
     * 取消入科病人列表
     * @author CTQ
     * @return
     */
    public List<BaseDto> cacelPatientlist(String wardCode);

    /**
     * 确认取消入科
     * @param comeDeptVo
     * @return
     */
    public String cancelComeDept(ComeDeptVo comeDeptVo);
    /**
     * 可被取消离院的病人列表
     * @param comeDeptVo
     * @return
     */
    public List<BaseDto> cancelLeavePatientlist(ComeDeptVo comeDeptVo);
    /**
     * 确认取消离院
     * @param comeDeptVo
     * @return
     */
    public String cancelLeaveHosp(ComeDeptVo comeDeptVo);


    public String test(ComeDeptVo comeDeptVo);

    /**
     * 根据参数查询在院病人列表
     * @param patsInHospital
     * @author CTQ
     * @return
     */
    public  List<BaseDto>  findAllInHospList(PatsInHospital patsInHospital);
}
