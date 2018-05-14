package com.publiclockerserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class AddressQueryServlet
 */
@WebServlet("/AddressQueryServlet")
public class LockerRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LockerRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	int i =0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println(i);
		i++;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("do post");

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		str = sb.toString();
		System.out.println(str);
		br.close();
		Gson gson = new Gson();
		AddressRequest_VO clientRequest = gson.fromJson(str, AddressRequest_VO.class);
		
		List<Map<String, String>> addd = new ArrayList<Map<String, String>> ();
		Map<String,String> map = new HashMap<String,String>();
		
		for(int i =0;i<3;i++) {
			map.put("address", String.valueOf(i));
			map.put("city", String.valueOf(i));
			map.put("state", String.valueOf(i));
			map.put("zipcode", String.valueOf(i));
			addd.add(map);
			
		}
		 
		String gg=gson.toJson(addd);
		System.out.println("gg:"+addd);
		System.out.println("gg:"+gg);

//		if(new VerificationDaoImpl().verification(order.getApiKey(), "CustomerInfo"	)) {
//			LockerRequestDao lockerRequest = LockerRequestDaoFactory.getLockerRequestDaoInstance();
//			List<Map<String, String>> addrList = lockerRequest.addressQuery(clientRequest.getApiKey(),
//					clientRequest.getOrderNumber(), clientRequest.getPackageType(), clientRequest.getPackageQty(),
//					clientRequest.getZipcode());
//			
//
//			
//			
//
//			
//			
//
//		}
		
		
        response.getOutputStream().write(gg.getBytes());
	 
//		PrintWriter out = response.getWriter();
//		out.println("Helloworrrd");
		
		
		
		
		
		//doGet(request, response);

	}

}
