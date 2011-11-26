package com.runchain.exercise.admin.service.venue;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runchain.exercise.admin.butil.venue.VenuePictureUtil;
import com.runchain.exercise.admin.dao.venue.VenuePictureDao;
import com.runchain.exercise.admin.entity.venue.VenuePicture;

/**
 * 场馆图片实体管理类
 *
 * @author HenryYan
 *
 */
@Service
@Transactional
public class VenuePictureManager {
	
	@Autowired VenuePictureDao pictureDao;
	@Autowired VenueInfoManager venueManager;

	/**
	 * 保存图片
	 * @param picture
	 */
	public void savePicture(VenuePicture picture) {
		pictureDao.save(picture);
	}
	
	/**
	 * 查询本场馆的图片
	 * @param venueId	场馆ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<VenuePicture> findPictures(Long venueId) {
		return pictureDao.findBy("venueId", venueId);
	}

	/**
	 * 获取图片对象
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public VenuePicture getPicture(Long id) {
		return pictureDao.get(id);
	}
	
	/**
	 * 删除图片
	 * @param id
	 */
	public void deletePicture(Long id) {
		
		VenuePicture picture = getPicture(id);
		ServletContext servletContext = ServletActionContext.getServletContext();
		Long tempVenueId = picture.getVenueId();
		String realPath = servletContext.getRealPath(VenuePictureUtil.VENUE_PICTURE_PATH + tempVenueId);
		String pictureRealName = picture.getPictureRealName();
		String fullPicPath = realPath + "/" + pictureRealName;
		
		// 删除主图片
		FileUtils.deleteQuietly(new File(fullPicPath));
		
		// 删除缩略图
		List<Integer[]> thumbnailsSizes = VenuePictureUtil.getThumbnailsSizes();
		for (Integer[] sizes : thumbnailsSizes) {
			File tempThumFile = new File(realPath + "/thumbnails/" + sizes[0] + "/" + pictureRealName);
			if (tempThumFile.exists()) {
				FileUtils.deleteQuietly(tempThumFile);
			}
		}
		

		String venuePicture = venueManager.getVenuePicture(tempVenueId);
		if (venuePicture.equals(pictureRealName)) {
			venueManager.setVenuePicture(tempVenueId, null);
		}
		
		pictureDao.delete(id);
	}
	
}
