package com.publiclockerserver;

public class DaoFactory {
	public static ConfirmOrderDaoImpl getConfirmOrderDaoInstance(Order_VO order) {
		return new ConfirmOrderDaoImpl(order);
	}

	public static VerificationDaoImpl getVerificationDaoInstance() {
		return new VerificationDaoImpl();
	}

	static LockerRequestDaoImpl getLockerRequestDaoInstance() {
		return new LockerRequestDaoImpl();
	}
}
