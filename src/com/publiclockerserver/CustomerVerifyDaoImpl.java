package com.publiclockerserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

class CustomerVerifyDaoImpl implements CustomerVerifyDao {

	@Override
	public

	boolean apiKeyVerify(String apiKey) {
		String sql = "select apiKey from CustomerInfo where apiKey =" + apiKey;
		Connection conn = C3p0Utils.getConnection();
		ResultSet rs = C3p0Utils.getResultSet(conn, sql);
		boolean flag = false;
		try {
			if (rs.getString("apiKey").equals(apiKey)) {
				flag = true;
			} else
				flag = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Utils.close(rs);
			C3p0Utils.close(conn);
		}
		return flag;
	}

}
