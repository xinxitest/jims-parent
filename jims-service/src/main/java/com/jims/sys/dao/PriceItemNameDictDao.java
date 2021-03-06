/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.PriceItemNameDict;

/**
 * 价表项目名称DAO接口
 * @author 罗海昆
 * @version 2016-04-26
 */
@MyBatisDao
public interface PriceItemNameDictDao extends CrudDao<PriceItemNameDict> {
	
}