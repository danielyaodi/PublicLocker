package com.publiclockerserver;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.publiclockerserver.utils.C3p0Utils;

public class purgeTimer {
	public static void purgeCommittedCellTimer(String orderNumber, List<String> committedCellList) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// SQL for purge committed cell in LockerCellInfo
				String cellCommittedPurgeSQL = SQLstatement.getUpdateInSQL("LockerCellInfo", "cellCommitted", "0",
						"cellID", String.join(",", committedCellList));
				C3p0Utils.executeUpdate(cellCommittedPurgeSQL); // purge committed cell in LockerCellInfo

				// SQL for delete same orderNumber Record in AssignedToCusomter;
				String removeCommitSQL = SQLstatement.getDeleteSQL("AssignedToCustomer", "orderNumber", orderNumber);
				C3p0Utils.executeUpdate(removeCommitSQL);// delete same orderNumber Record in AssignedToCusomter;
			}

		}, 300000);

	}
}
