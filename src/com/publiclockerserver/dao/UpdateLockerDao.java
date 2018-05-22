package com.publiclockerserver.dao;

import java.util.List;
import java.util.Map;

import com.publiclockerserver.pojo.AddLocker_VO;

public interface UpdateLockerDao {
	public String showAllLockers();

	public String showAllCellsInLocker(String lockerID);

	public void deleteLocker(String lockerID);

	public void updateLockerAddress(AddLocker_VO lockerAddr, String lockerID);

	public void deleteCell(String cellID);
}
