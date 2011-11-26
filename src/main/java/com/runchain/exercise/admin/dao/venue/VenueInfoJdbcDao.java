package com.runchain.exercise.admin.dao.venue;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

/**
 * 场馆JDBC DAO
 *
 * @author HenryYan
 *
 */
@Repository
public class VenueInfoJdbcDao extends JdbcTemplate {

	@Autowired
	public VenueInfoJdbcDao(DataSource dataSource) {
		super(dataSource);
	}
	
	/**
	 * 执行删除场馆信息的存储过程
	 * @param venueId	场馆ID
	 */
	public void deleteVenueInfos(final Long venueId) {
		String sql = "{call delete_venue_infos(?)}";
		super.execute(sql, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setLong(1, venueId);
				return ps.execute();
			}
			
		});
	}
	
}
