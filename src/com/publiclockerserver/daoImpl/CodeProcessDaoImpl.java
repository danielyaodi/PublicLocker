package com.publiclockerserver.daoImpl;

import java.sql.Connection;

import com.publiclockerserver.Constant;
import com.publiclockerserver.SQLstatement;
import com.publiclockerserver.dao.*;
import com.publiclockerserver.utils.*;
import com.publiclockerserver.*;

public class CodeProcessDaoImpl implements CodeProcessDao {

	@Override
	public void deliveryCode(String cellID) {
		Connection conn = C3p0Utils.getConnection();
		String insertHistorySQL = SQLstatement.getInsertHistorySQL(cellID);
		C3p0Utils.executeUpdate(conn, insertHistorySQL); // insert from ConfirmedOrder to History

		String delDeliveryCodeSQL = SQLstatement.getUpdateSQL(Constant.CONFIRMED_ORDER, Constant.DELIVERY_CODE, "''",
				Constant.CELL_ID, BeanUtils.addSingleQuote(cellID));
		C3p0Utils.executeUpdate(conn, delDeliveryCodeSQL); // delete deliveryCode from ConfirmedOrder, so that
															// deliveryCode cannot be used second time.

		String insertDeliveryTimeSQL = SQLstatement.getUpdateSQL(Constant.HISTORY, Constant.DELIVERY_TIME, "NOW()",
				Constant.CELL_ID, BeanUtils.addSingleQuote(cellID));

		C3p0Utils.executeUpdate(conn, insertDeliveryTimeSQL); // insert deliveryTime to History table.

		C3p0Utils.close(conn);
		BeanUtils.openLockerDoor(); // open cell door.
	}

	@Override
	public void pickupCode(String cellID) {
		Connection conn = C3p0Utils.getConnection();
		String insertPickupTimeSQL = SQLstatement.getUpdateSQL(Constant.HISTORY, Constant.PICKUP_TIME, "NOW()",
				Constant.CELL_ID, BeanUtils.addSingleQuote(cellID));
		C3p0Utils.executeUpdate(conn, insertPickupTimeSQL); // insert pickupTime to History table.

		String deleteConfirmedOrderSQL = SQLstatement.getDeleteSQL(Constant.CONFIRMED_ORDER, Constant.CELL_ID,
				BeanUtils.addSingleQuote(cellID));
		C3p0Utils.executeUpdate(conn, deleteConfirmedOrderSQL); // delete the confirmedOrder record.
		BeanUtils.openLockerDoor();

		String updateTransResultSQL = SQLstatement.getUpdateSQL(Constant.HISTORY, Constant.TRANSACTION_RESULT, "1",
				Constant.CELL_ID, BeanUtils.addSingleQuote(cellID));
		C3p0Utils.executeUpdate(conn, updateTransResultSQL); // update transaction result to 1, 1=transaction done.
																// otherwise transaction failed.

		C3p0Utils.close(conn);

	}

}
