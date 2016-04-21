/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.api.ElectronEnterHospitalServiceApi;
import com.jims.clinic.dao.ElectronEnterHospitalDao;
import com.jims.clinic.entity.ElectronEnterHospital;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 病历文书--入院记录Service
 * @author zhaonig
 * @version 2016-04-20
 */
@Service
@Transactional(readOnly = true)
public  class ElectronEnterHospitalServiceImpl extends CrudImplService<ElectronEnterHospitalDao,ElectronEnterHospital> implements ElectronEnterHospitalServiceApi {
    @Autowired
	private ElectronEnterHospitalDao electronEnterHospitalDao;

	@Override
	public ElectronEnterHospital getElectronEnteHos(String patVisitId) {
		return null;
	}
}