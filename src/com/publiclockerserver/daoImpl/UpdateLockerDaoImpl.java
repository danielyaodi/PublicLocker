package com.publiclockerserver.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.publiclockerserver.SQLstatement;
import com.publiclockerserver.dao.UpdateLockerDao;
import com.publiclockerserver.pojo.AddLocker_VO;
import com.publiclockerserver.utils.BeanUtils;
import com.publiclockerserver.utils.C3p0Utils;

public class UpdateLockerDaoImpl implements UpdateLockerDao {
	Connection conn = C3p0Utils.getConnection();

	@Override
	public String showAllLockers() {
		ResultSet rs = C3p0Utils.getResultSet(conn, SQLstatement.showAllLockersSQL);

		return BeanUtils.getJsonStringFromRS(rs);
	}

	@Override
	public String showAllCellsInLocker(String lockerID) {
		String showAllCellsInLockerSQL = SQLstatement.getShowAllCellsInLockerSQL(lockerID);
		ResultSet rs = C3p0Utils.getResultSet(conn, showAllCellsInLockerSQL);

		return BeanUtils.getJsonStringFromRS(rs);
	}

	@Override
	public void deleteLocker(String lockerID) {
		String deleteLockerSQL = SQLstatement.getDeleteLockerSQL(lockerID);
		C3p0Utils.executeUpdate(conn, deleteLockerSQL);
		String deleteCellsSQL = SQLstatement.getDeleteCellsSQL(lockerID);
		C3p0Utils.executeUpdate(conn, deleteCellsSQL);

	}

	@Override
	public void updateLockerAddress(AddLocker_VO lockerAddr, String lockerID) {
		PreparedStatement pstmt = C3p0Utils.getPstmt(conn, SQLstatement.UpdateLockerAddressSQL);
		try {
			pstmt.setString(1, lockerAddr.getStreet());
			pstmt.setString(2, lockerAddr.getCity());
			pstmt.setString(3, lockerAddr.getState());
			pstmt.setInt(4, lockerAddr.getZipcode());
			pstmt.setString(5, lockerID);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				C3p0Utils.close(pstmt);

			}
		}
	}

	@Override
	public void deleteCell(String cellID) {
		String deleteOneCellSQL = SQLstatement.getDeleteOneCellSQL(cellID);
		C3p0Utils.executeUpdate(conn, deleteOneCellSQL);
	}

}
