package com.runchain.exercise.admin.web.venue;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.exercise.admin.butil.venue.VenuePictureUtil;
import com.runchain.exercise.admin.entity.venue.VenuePicture;
import com.runchain.exercise.admin.service.venue.VenueInfoManager;
import com.runchain.exercise.admin.service.venue.VenuePictureManager;
import com.runchain.arch.util.date.DateUtil;
import com.runchain.arch.util.file.FileUtil;

/**
 * 场馆图片Action，提供上传、批量上传功能
 *
 * @author HenryYan
 *
 */
@Action
@Namespace("/venue")
public class VenuePictureAction extends CrudActionSupport<VenuePicture> {

	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected VenuePictureManager pictureManager;
	@Autowired
	protected VenueInfoManager venueManager;

	private Long id;
	private Long venueId;
	private String pictureRealName;
	private VenuePicture picture;

	private File[] uploadImages;//得到上传的文件
	private String[] uploadImagesContentType;//得到文件的类型
	private String[] uploadImagesFileName;//得到文件的名称

	List<VenuePicture> pictures = new ArrayList<VenuePicture>();

	//这里略省了属性的getter/setter方法
	public String upload() throws Exception {
		if (venueId == null) {
			Struts2Utils.renderText("请选择场馆！");
			return null;
		}
		String realpath = ServletActionContext.getServletContext().getRealPath("/venue/pictures/" + venueId);
		try {
			File file = new File(realpath);
			if (!file.exists()) {
				file.mkdirs();
			}

			for (int i = 0; i < uploadImages.length; i++) {
				String tempRealName = uploadImagesFileName[i];
				String pictureType = uploadImagesContentType[i];
				logger.debug("准备复制场馆图片 ：{}，类型：{}", tempRealName, pictureType);
				File uploadImage = uploadImages[i];

				String fileType = FileUtil.getFileType(tempRealName);
				String newFilePreName = DateUtil.getSysdate() + "-" + System.currentTimeMillis();
				String realName = newFilePreName + "." + fileType;
				logger.debug("场馆图片上传，由：{} 改名为：{}", tempRealName, realName);

				File destFile = new File(file, realName);
				FileUtils.copyFile(uploadImage, destFile);

				/*
				 * 生成缩略图
				 */
				String thumbnailsPath = realpath + "/thumbnails";
				File scaleDir = new File(thumbnailsPath);
				if (!scaleDir.exists()) {
					scaleDir.mkdir();
				}
				String fromFileStr = destFile.getPath();
				try {

					List<Integer[]> thumbnailsSizes = VenuePictureUtil.getThumbnailsSizes();
					for (Integer[] sizes : thumbnailsSizes) {
						// 创建存放不同大小的图片文件夹，以宽度为名称
						String strThumFloder = thumbnailsPath + "/" + sizes[0];
						File thumFloder = new File(strThumFloder);
						if (!thumFloder.exists()) {
							thumFloder.mkdir();
						}

						String thumbnailsFilePath = strThumFloder + "/" + realName;
						logger.debug("生成缩略图：fromFileStr={}，scaleFilePath={}", fromFileStr, thumbnailsFilePath);
						Thumbnails.of(destFile).size(sizes[0], sizes[1])
								.outputFormat(fileType)
								.toFile(new File(thumbnailsFilePath));
					}

				} catch (Exception e) {
					logger.error("生成缩略图出错：", e);
				}

				// 保存到数据库
				VenuePicture picture = new VenuePicture();
				picture.setVenueId(venueId);
				picture.setPictureRealName(realName);
				picture.setUploadDate(new Date());
				picture.setPictureSize(uploadImages[i].length());

				String venuePicture = venueManager.getVenuePicture(venueId);
				if (StringUtils.isBlank(venuePicture)) {
					venueManager.setVenuePicture(venueId, realName);
				}

				pictureManager.savePicture(picture);

				logger.debug("完成复制场馆图片 ：{}，类型：{}", realName, pictureType);
			}
			Struts2Utils.renderText(SUCCESS);

		} catch (Exception e) {
			logger.error("上传图片失败，", e);
			Struts2Utils.renderText("上传失败！");
		}
		return null;
	}

	@Override
	public String delete() throws Exception {
		try {

			pictureManager.deletePicture(id);
			logger.debug("删除场馆图片：{}", id);
			Struts2Utils.renderText(SUCCESS);
		} catch (Exception e) {
			logger.error("删除场馆图片出错：", e);
		}
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		pictures = pictureManager.findPictures(venueId);
		String venuePicture = venueManager.getVenuePicture(venueId);
		Struts2Utils.getRequest().setAttribute("majorPicture", venuePicture);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			picture = pictureManager.getPicture(id);
		} else {
			picture = new VenuePicture();
		}
	}

	@Override
	public String save() throws Exception {
		try {
			pictureManager.savePicture(picture);
			Struts2Utils.renderText(SUCCESS);
		} catch (Exception e) {
			logger.error("更新图片信息出错：", e);
		}
		return null;
	}

	/**
	 * 设置场馆的主图片
	 * @return
	 */
	public String setMajorPicture() {
		try {
			venueManager.setVenuePicture(venueId, pictureRealName);
			Struts2Utils.renderText(SUCCESS);
		} catch (Exception e) {
			logger.error("设置场馆：" + venueId + "的主图片为：" + pictureRealName + "出错：", e);
		}
		return null;
	}

	@Override
	public VenuePicture getModel() {
		return picture;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUploadImages(File[] uploadImages) {
		this.uploadImages = uploadImages;
	}

	public void setUploadImagesContentType(String[] uploadImagesContentType) {
		this.uploadImagesContentType = uploadImagesContentType;
	}

	public void setUploadImagesFileName(String[] uploadImagesFileName) {
		this.uploadImagesFileName = uploadImagesFileName;
	}

	public List<VenuePicture> getPictures() {
		return pictures;
	}

	public void setPictureRealName(String pictureRealName) {
		this.pictureRealName = pictureRealName;
	}

}
