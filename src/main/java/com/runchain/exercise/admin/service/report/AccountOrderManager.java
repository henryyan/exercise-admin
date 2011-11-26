package com.runchain.exercise.admin.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runchain.arch.orm.dao.BaseHibernateDao;
import com.runchain.arch.service.base.BaseEntityManager;
import com.runchain.exercise.admin.dao.report.AccountOrderDao;
import com.runchain.exercise.admin.entity.order.AccountOrder;

/**
 * 付款订单Manager
 *
 * @author HenryYan
 *
 */
@Service
@Transactional(readOnly = true)
public class AccountOrderManager extends BaseEntityManager<AccountOrder, Long> {
	
	@Autowired
	AccountOrderDao dao;

	@Override
	public BaseHibernateDao<AccountOrder, Long> getDao() {
		return dao;
	}

}
