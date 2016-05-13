/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugStock;

/**
 * 药品库存DAO接口
 * @author zhaoning
 * @version 2016-04-22
 */
@MyBatisDao
public interface DrugStockDao extends CrudDao<DrugStock> {
	
}