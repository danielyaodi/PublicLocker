package com.publiclockerserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

class VerificationDaoImpl implements VerificationDao {

	@Override
	public

			boolean verification(String column, String table, String value) {
		String sql = SQLstatement.verificationSQL(column, table, value);
		Connection conn = C3p0Utils.getConnection();
		ResultSet rs = C3p0Utils.getResultSet(conn, sql);
		boolean flag = false;
		try {
			if (rs.getString(column).equals(value)) {
				flag = true;
			} else
				flag = false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.close(rs);
			C3p0Utils.close(conn);
		}
		return flag;
	}
 
	
	
	
	

}
