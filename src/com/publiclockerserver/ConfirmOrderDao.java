package com.publiclockerserver;

import java.util.Map;

public interface ConfirmOrderDao {
		Map<String,String> confirmOrder(String apiKey, String orderNumber, String lockerID);
}