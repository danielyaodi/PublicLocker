package com.publiclockerserver;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Test {

	public static void main(String[] args) {
		int[] arr = { 12345, 23456, 34567, 4567, 56789 };
		//
		// List ss =new ArrayList();
		// ss.add("'aaa'");
		// ss.add("aaa");
		// ss.add("aaa");
		// ss.add("'123'");
		//
		// String sss = String.join("-", ss);
		//
		// String s = Arrays.toString(arr);
		// System.out.println(sss);
		// System.out.println(ss);
		// String s =
		// "{\"apiKey\":\"apiKey1\",\"orderNumber\":\"orderNumber1\",\"packageType\":0,\"packageQty\":2,\"zipcode\":[91001,91002]}";
		// Gson gson = new Gson();
		//
		// AddressRequest_VO av = new AddressRequest_VO("apikey1", "order1", 1, 2, arr);
		//
		// String s1 = gson.toJson(av);
		// JsonParser parser = new JsonParser();
		// JsonObject jo = parser.parse(request).getAsJsonObject();
		//
		//
		//
		//
		//
		//
		// System.out.println(s1);
		// System.out.println(jo.get("apiKey"));

		String s = UUID.randomUUID().toString();
		System.out.println(s);
		s = s.replace("-", "");
		System.out.println(s);
		
	}

}
