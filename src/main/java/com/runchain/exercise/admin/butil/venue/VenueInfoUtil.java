package com.runchain.exercise.admin.butil.venue;

import javax.servlet.http.HttpSession;

import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.exercise.admin.entity.venue.VenueInfo;

/**
 * 场馆工具类.
 *
 * @author HenryYan
 *
 */
public class VenueInfoUtil {

	public static final String CURRENT_VENUE = "venue";
	
	/**
	 * 保存场馆到Session
	 * @param session	{@link HttpSession}
	 * @param venue		{@link VenueInfo}
	 */
	public static void saveVenue2Session(VenueInfo venue) {
		Struts2Utils.getSession().setAttribute(CURRENT_VENUE, venue);
	}
	
	/**
	 * 从Session中获取当前管理的场馆
	 * @param session	{@link HttpSession}
	 * @return	有则返回当前管理的场馆对象，没有则返回NULL
	 */
	public static VenueInfo getVenueFromSession() {
		Object attribute = Struts2Utils.getSession().getAttribute(CURRENT_VENUE);
		if (attribute != null) {
			return (VenueInfo) attribute;
		}
		return null;
	}
	
	/**
	 * 从Session中移除场馆信息
	 * @param session	{@link HttpSession}
	 */
	public static void removeVenue(HttpSession session) {
		session.removeAttribute(CURRENT_VENUE);
	}
	
}
