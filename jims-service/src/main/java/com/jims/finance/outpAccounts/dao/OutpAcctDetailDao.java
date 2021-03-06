package com.jims.finance.outpAccounts.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.outpAccounts.entity.OutpAcctDetail;

/**
 * 门诊收费结帐明细记录DAO接口
 * @author pq
 * @version 2016-06-01
 */
@MyBatisDao
public interface OutpAcctDetailDao extends CrudDao<OutpAcctDetail> {
	
}