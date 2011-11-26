package com.runchain.exercise.admin.dao.venue;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.venue.VenuePicture;

/**
 * 场馆图片DAO
 *
 * @author HenryYan
 *
 */
@Repository
public class VenuePictureDao extends HibernateDao<VenuePicture, Long> {

}
