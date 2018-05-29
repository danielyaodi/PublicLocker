package com.publiclockerserver.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import com.publiclockerserver.DaoFactory;
import com.publiclockerserver.SQLstatement;
import com.publiclockerserver.dao.ClientDao;
import com.publiclockerserver.utils.C3p0Utils;

public class ClientDaoImpl implements ClientDao {

	@Override
	public Boolean clientLogin(String userName, String password, String apiKey) {
		return DaoFactory.getVerificationDaoInstance().verification("userName", "password", "CustomerInfo", userName,
				password, apiKey);

	}

	@Override
	public void clientRegister(String userName, String password, String customerName) {
		Connection conn = C3p0Utils.getConnection();
		PreparedStatement pstmt = C3p0Utils.getPstmt(conn, SQLstatement.newClientSQL);
		String newAPIkey = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(newAPIkey + userName + password + customerName);
		try {
			pstmt.setString(1, newAPIkey);
			pstmt.setString(2, userName);
			pstmt.setString(3, password);
			pstmt.setString(4, customerName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				C3p0Utils.close(pstmt);
			}
			if (conn != null) {
				C3p0Utils.close(conn);

			}
		}

	}

}
