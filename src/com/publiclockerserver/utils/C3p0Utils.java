package com.publiclockerserver.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Utils {

	static ComboPooledDataSource dataSource = new ComboPooledDataSource("mySource");

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	public static PreparedStatement getPstmt(Connection conn, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public static ResultSet getResultSet(Connection conn, String sql) {

		ResultSet rs = null;
		PreparedStatement pstmt = getPstmt(conn, sql);
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return rs;
	}

	public static void executeUpdate(String sql) {

		Connection conn = C3p0Utils.getConnection();
		PreparedStatement pstmt = C3p0Utils.getPstmt(conn, sql);
		try {
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

	public static void executeUpdate(Connection conn, String sql) {

		PreparedStatement pstmt = C3p0Utils.getPstmt(conn, sql);
		try {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				C3p0Utils.close(pstmt);
			}

		}

	}

	public static void close(Connection conn) {

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public static void close(ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
