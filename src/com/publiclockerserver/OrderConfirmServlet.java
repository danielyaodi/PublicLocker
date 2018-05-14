package com.publiclockerserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))	;
		String str=null;
		StringBuilder sb = new StringBuilder();
		while((str=br.readLine())!=null) {
			sb.append(str);
		}
			str=sb.toString();
		
		Order_VO order = new Gson().fromJson(str, Order_VO.class);
		
		if(new VerificationDaoImpl().verification(order.getApiKey(), "CustomerInfo"	)) {
			
			if (new VerificationDaoImpl().verification(order.getOrderNumber(), "AssignedToCustomer")) {
					
				
				String orderCode = new confirmOrder(order.getApiKey(),order.getOrderNumber(),order.getLockerID());				
				
				
			}			
			
			
		}
		
		
		
	}

}
