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

@WebServlet("/AddressQueryServlet")
public class LockerRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LockerRequestServlet() {
		super();
	}

	int i = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		System.out.println("***********"+i);
		i++;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("do post");

		String requestStr = BeanUtils.servletRequestReader(request.getInputStream());   //read all json file from request.

		Gson gson = new Gson();
		AddressRequest_VO addressRequest = gson.fromJson(requestStr, AddressRequest_VO.class);  // convert json to AddressRequest_VO class

		if (BeanUtils.checkAPIKey(addressRequest.getApiKey())) {			//verify client valid APIKey.
			List<Map<String, String>> addressMapList = DaoFactory.getLockerRequestDaoInstance(addressRequest)
					.addressQuery();			//get address list<map>;

			String addressJson = gson.toJson(addressMapList);  //convert to json;

			response.getOutputStream().write(addressJson.getBytes());		// send json back to client through response obj.

		}

	}

}
