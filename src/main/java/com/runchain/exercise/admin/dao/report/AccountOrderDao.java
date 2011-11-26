package com.runchain.exercise.admin.dao.report;

import org.springframework.stereotype.Repository;

import com.runchain.arch.orm.dao.BaseHibernateDao;
import com.runchain.exercise.admin.entity.order.AccountOrder;

/**
 * 付款订单DAO
 *
 * @author HenryYan
 *
 */
@Repository
public class AccountOrderDao extends BaseHibernateDao<AccountOrder, Long> {

}
