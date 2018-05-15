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
				String cellCommittedSQL = SQLstatement.cellCommittedToZeroSQL(cellCommitList);
				C3p0Utils.executeUpdate(cellCommittedSQL);
				String removeCommitSQL = SQLstatement.removeCommitSQL(orderNumber);
				C3p0Utils.executeUpdate(removeCommitSQL);
			}

		}, 300000);

	}
}
