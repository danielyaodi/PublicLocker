package com.publiclockerserver.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import com.publiclockerserver.SQLstatement;
import com.publiclockerserver.dao.AddLockerDao;
import com.publiclockerserver.pojo.AddLocker_VO;
import com.publiclockerserver.utils.C3p0Utils;

public class AddLockerDaoImpl implements AddLockerDao {
	public AddLockerDaoImpl(AddLocker_VO addLocker) {
		super();
		this.cellTypeIQty = addLocker.getCellTypeIQty();
		this.cellTypeIIQty = addLocker.getCellTypeIIQty();
		this.cellTypeIIIQty = addLocker.getCellTypeIIIQty();
		this.street = addLocker.getStreet();
		this.city = addLocker.getCity();
		this.state = addLocker.getState();
		this.zipcode = addLocker.getZipcode();
	}

	int cellTypeIQty;
	int cellTypeIIQty;
	int cellTypeIIIQty;
	String street, city, state, newLockerID, newCellID;
	int zipcode;
	Connection conn = C3p0Utils.getConnection();
	PreparedStatement pstmt;

	@Override
	public void addLocker() {

		insertNewLockerAddress();
		insertNewCellInfo(cellTypeIQty, 1);
		insertNewCellInfo(cellTypeIIQty, 2);
		insertNewCellInfo(cellTypeIIIQty, 3);
		if (conn != null) {
			C3p0Utils.close(conn);
		}
	}

	private void insertNewCellInfo(int cellTypeQty, int cellType) {
		pstmt = C3p0Utils.getPstmt(conn, SQLstatement.insertNewCellSQL);
		try {
			for (int i = 0; i < cellTypeQty; i++) {
				newCellID = UUID.randomUUID().toString().replaceAll("-", "");

				pstmt.setString(1, newCellID);
				pstmt.setString(2, newLockerID);
				pstmt.setInt(3, cellType);
				pstmt.addBatch();

			}
			pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				C3p0Utils.close(pstmt);
			}
		}

	}

	public void insertNewLockerAddress() {
		pstmt = C3p0Utils.getPstmt(conn, SQLstatement.insertNewLockerSQL);
		newLockerID = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			pstmt.setString(1, newLockerID);
			pstmt.setString(2, street);
			pstmt.setString(3, city);
			pstmt.setString(4, state);
			pstmt.setInt(5, zipcode);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				C3p0Utils.close(pstmt);
			}
		}

	}
}
