package com.publiclockerserver;

import com.publiclockerserver.daoImpl.*;
import com.publiclockerserver.pojo.*;

public class DaoFactory {
	public static ConfirmOrderDaoImpl getConfirmOrderDaoInstance(Order_VO order) {
		return new ConfirmOrderDaoImpl(order);
	}

	public static VerificationDaoImpl getVerificationDaoInstance() {
		return new VerificationDaoImpl();
	}

	public static LockerRequestDaoImpl getLockerRequestDaoInstance(AddressRequest_VO address) {
		return new LockerRequestDaoImpl(address);
	}

	public static CodeProcessDaoImpl getCodeProcessDaoInstance() {
		return new CodeProcessDaoImpl();
	}

	public static AddLockerDaoImpl getAddLockerDaoInstance(AddLocker_VO addLocker) {
		return new AddLockerDaoImpl(addLocker);
	}

	public static UpdateLockerDaoImpl getUpdateLockerDaoInstance() {
		return new UpdateLockerDaoImpl();
	}

}
