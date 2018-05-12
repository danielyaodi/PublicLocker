package com.publiclockerserver;

import java.util.List;
import java.util.Map;

public interface LockerRequestDao {
	public List<Map<String,String>> addressQuery(String apiKey,String orderNumber, int cellType,int packageQty, int[]zipcode );
	
	
	
	
}
