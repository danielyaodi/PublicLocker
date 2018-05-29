package com.publiclockerserver.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.publiclockerserver.DaoFactory;
import com.publiclockerserver.utils.BeanUtils;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClientServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		if (action.equals("login")) {
			String apiKey = "";
			if (DaoFactory.getClientDaoInstance().clientLogin(request.getParameter("userName"),
					request.getParameter("password"), apiKey)) {
				// get customer APIkey after successful login
				request.setAttribute("apiKey", apiKey);
				request.getRequestDispatcher("/client.jsp").forward(request, response);

			} else {

				request.getRequestDispatcher("/clientLogin.jsp").forward(request, response);

			}

		} else if (action.equals("register")) {

			DaoFactory.getClientDaoInstance().clientRegister(request.getParameter("userName"),
					request.getParameter("password"), request.getParameter("customerName"));

			request.getRequestDispatcher("/clientLogin.jsp").forward(request, response);
		} else if (action.equals("showClientRecord")) {

		} else {

			request.getRequestDispatcher("/clientLogin.jsp").forward(request, response);

		}

	}

}
