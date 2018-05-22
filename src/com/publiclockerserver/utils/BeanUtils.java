package com.publiclockerserver.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletInputStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.publiclockerserver.DaoFactory;

public class BeanUtils {
	public static String getStringFromRS(ResultSet rs, String column) {
		String str = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			while (rs.next()) {
				list.add(rs.getString(column));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		str = String.join(",", list);

		return str;
	}

	// printable char[] use for
	static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
			"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };

	// 8 bit uuid, no duplicate in 10 millions test.
	public static String getShortUUID() {

		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]); // %62
		}
		return shortBuffer.toString();

	}

	public static Boolean checkAPIKey(String apiKey) {
		return DaoFactory.getVerificationDaoInstance().verification("apiKey", "CustomerInfo", apiKey);
	}

	public static Boolean checkOrderNumber(String orderNumber) {
		return DaoFactory.getVerificationDaoInstance().verification("orderNumber", "AssignedToCustomer", orderNumber);
	}

	// ************ ***********
	public static int checkCode(String cellID, String code) {
		return DaoFactory.getVerificationDaoInstance().verification(cellID, code);
	}

	public static String servletRequestReader(ServletInputStream inputStream) {

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();
		String str = null;
		try {
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		str = sb.toString();
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}

	public static String addSingleQuote(String str) {
		return new String("'" + str + "'");
	}

	public static void openLockerDoor() {
		// TODO Auto-generated method stub

	}

	public static String getJsonStringFromRS(ResultSet rs) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		Gson gson = new Gson();
		int columnCount;
		try {
			while (rs.next()) {

				ResultSetMetaData metaData = rs.getMetaData();
				columnCount = metaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {

					String columnName = metaData.getColumnLabel(i);

					String value = rs.getString(columnName);

					map.put(columnName, value);

				}
				list.add(map);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gson.toJson(list);
	}
	
	public static String jsonObjectToJsonArray(String str) {
		return "["+str.substring(1,str.length()-1)+"]";
	}
	

}
