package com.publiclockerserver;

public class LockerRequestDaoFactory {
	static LockerRequestDaoImpl getLockerRequestDaoInstance() {
		return new LockerRequestDaoImpl();
	}
}
