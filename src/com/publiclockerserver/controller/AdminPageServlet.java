package com.publiclockerserver.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.publiclockerserver.utils.BeanUtils;

@WebServlet("/AdminPageServlet")
public class AdminPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminPageServlet() {
		super();
	}

	String getForward = "empty";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			getForward = action;
		}
		System.out.println("getFOrdd in get" + getForward);
		if (getForward.equals("add")) {
			System.out.println(getForward);
			// getForward = "empty";
			request.getRequestDispatcher("/AddLocker.jsp").forward(request, response);
		} else if (getForward.equals("update")) {
			System.out.println(getForward);
			// getForward = "empty";
			request.getRequestDispatcher("/updateLocker.jsp").forward(request, response);
		} else if (getForward.equals("client")) {
			System.out.println(getForward);
			// getForward = "empty";
			request.getRequestDispatcher("/ClientView.jsp").forward(request, response);
		} else {
			System.out.println("response in get");

			response.sendRedirect("http://127.0.0.1:8080/PublicLockerServer/admin.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String getForward = (String) request.getParameter("action");
		// System.out.println(action + "in post");
		// if (action.equals("add")) {
		// getForward = action;
		// System.out.println("action in post:" + action);
		// request.getRequestDispatcher("/AddLocker.jsp").forward(request, response);
		// } else if (action.equals("update")) {
		// getForward = action;
		// System.out.println("action in post:" + action);
		// // request.getRequestDispatcher("/updateLocker.jsp").forward(request,
		// response);
		// } else if (action.equals("client")) {
		// System.out.println("action in post:" + action);
		// getForward = action;
		// // request.getRequestDispatcher("/ClientView.jsp").forward(request,
		// response);
		// } else {
		// System.out.println("sendRedricet in post");
		// response.sendRedirect("http://127.0.0.1:8080/PublicLockerServer/admin.jsp");
		// }

	}

}
