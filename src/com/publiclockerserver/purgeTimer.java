package com.publiclockerserver;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class purgeTimer {
	public static void purgeCommittedCellTimer(String orderNumber, List<String> cellCommitList) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				String cellCommittedSql = "UPDATE LockerCellInfo SET cellCommitted = 0 WHERE cellID in ( "
						+ String.join(",", cellCommitList) + ")";
				C3p0Utils.executeUpdate(cellCommittedSql);
				String removeCommitSql = "DELETE FROM AssignedToCustomer WHERE orderNumber = '" + orderNumber + "'";
				C3p0Utils.executeUpdate(removeCommitSql);
			}

		}, 300000);

	}
}
