package com.publiclockerserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class OrderConfirmServlet
 */
@WebServlet("/OrderConfirmServlet")
public class OrderConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderConfirmServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

		String orderConfirmStr = BeanUtils.servletRequestReader(request.getInputStream()); // read all json file from
																							// request.

		Gson gson = new Gson();
		Order_VO order = gson.fromJson(orderConfirmStr, Order_VO.class); // convert json to Order_VO class

		if (BeanUtils.checkAPIKey(order.getApiKey())) { // verify if valid client APIkey

			if (BeanUtils.checkOrderNumber(order.getOrderNumber())) { // verify if valid orderNumber, timeout since
																		// request.

				Map<String, String> codeMap = DaoFactory.getConfirmOrderDaoInstance(order).confirmOrder(); //get deliveryCode & pickupCode;
				String codeStr = gson.toJson(codeMap);			//covert to json
				response.getOutputStream().write(codeStr.getBytes()); 		// send json back to client through response obj.
			}

		}

	}

}
