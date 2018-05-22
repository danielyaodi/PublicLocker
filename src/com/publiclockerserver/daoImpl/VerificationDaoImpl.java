package com.publiclockerserver.daoImpl;

import java.sql.Connection;
  
import java.sql.ResultSet;
import java.sql.SQLException;

import com.publiclockerserver.* ;
import com.publiclockerserver.utils.* ;
import com.publiclockerserver.dao.VerificationDao;

public class VerificationDaoImpl implements VerificationDao {

	@Override
	public boolean verification(String column, String table, String value) {
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

	@Override
	public int verification(String column, String value) {

		String sql = SQLstatement.codeVerificationSQL(column);
		Connection conn = C3p0Utils.getConnection();
		ResultSet rs = C3p0Utils.getResultSet(conn, sql);
		int codeType = 0; // codeType 1 = deliveryCode, 2 = pickupCode.
		try {

			if (rs.getString("deliveryCode").equals(value)) {
				codeType = 1;

			} else if (rs.getString("pickupCode").equals(value)) {
				codeType = 2;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.close(rs);
			C3p0Utils.close(conn);
		}
		return codeType;
	}

}