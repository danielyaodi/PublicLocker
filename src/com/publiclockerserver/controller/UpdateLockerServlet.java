package com.publiclockerserver.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.publiclockerserver.DaoFactory;
import com.publiclockerserver.daoImpl.UpdateLockerDaoImpl;
import com.publiclockerserver.utils.BeanUtils;

@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateLockerServlet" })
public class UpdateLockerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String action = "empty";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String param = request.getParameter("action");
		if (param != null) {
			action = param;
		}
		System.out.println("param=" + param + "  action=" + action);

		if (action.equals("updateCells")) {

			String lockerID = request.getParameter("lockerID");
			// cellJsonStr = updateLocker.showAllCellsInLocker(lockerID);

			// response.getWriter().write(BeanUtils.jsonObjectToJsonArray(cellJsonStr));
			System.out.println("get  jump=" + action);

			request.getRequestDispatcher("/updateCell.jsp").forward(request, response);

		} else if (action.equals("updateThisCells")) {

			String cellID = request.getParameter("cellID");

		} else if (action.equals("deleteThisCell")) {
		
		String cellID = request.getParameter("cellID");
		DaoFactory.getUpdateLockerDaoInstance().deleteCell(request.getParameter("lockerID"));
	}

		else {
			System.out.println("sdfsadasdf");
			response.getWriter().append("Served at: ").append(request.getContextPath());

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// UpdateLockerDaoImpl updateLocker = DaoFactory.getUpdateLockerDaoInstance();
		String action = request.getParameter("action");
		System.out.println(action);
		String s = "{{\"lockerID\":\"lockerID1\"," + "\"street\":\"street1\"," + "\"city\":\"city1\","
				+ "\"state\":\"state1\"," + "\"zipcode\":\"zipcode1\"," + "\"createTime\":\"createTime1\"},"

				+ "{\"lockerID\":\"lockerID1\"," + "\"street\":\"street1\"," + "\"city\":\"city1\","
				+ "\"state\":\"state1\"," + "\"zipcode\":\"zipcode1\"," + "\"createTime\":\"createTime1\"},"

				+ "	{\"lockerID\":\"lockerID1\"," + "\"street\":\"street1\"," + "\"city\":\"city1\","
				+ "\"state\":\"state1\"," + "\"zipcode\":\"zipcode1\"," + "\"createTime\":\"createTime1\"}}";
		String ss = "[{\"lockerID\":\"lockerID1\"," + "\"street\":\"street1\"," + "\"city\":\"city1\","
				+ "\"state\":\"state1\"," + "\"zipcode\":\"zipcode1\","
				+ "\"createTime\":\"createTime1\"},{\"lockerID\":\"lockerID1\"," + "\"street\":\"street1\","
				+ "\"city\":\"city1\"," + "\"state\":\"state2\"," + "\"zipcode\":\"zipcode1\","
				+ "\"createTime\":\"createTime1\"}]";

		String sss = "{{\"cellID\":\"cellID1\"," + "\"lockerID\":\"lockerID1\"," + "\"cellType\":\"cellType1\","
				+ "\"cellAvailability\":\"cellAvailability1\"," + "\"cellCommitted\":\"cellCommitted1\","
				+ "\"cellHealth\":\"cellHealth1\" }," + "{\"cellID\":\"cellID2\"," + "\"lockerID\":\"lockerID2\","
				+ "\"cellType\":\"cellType2\"," + "\"cellAvailability\":\"cellAvailability2\","
				+ "\"cellCommitted\":\"cellCommitted2\"," + "\"cellHealth\":\"cellHealth2\" },"
				+ "{\"cellID\":\"cellID3\"," + "\"lockerID\":\"lockerID3\"," + "\"cellType\":\"cellType3\","
				+ "\"cellAvailability\":\"cellAvailability3\"," + "\"cellCommitted\":\"cellCommitted3\","
				+ "\"cellHealth\":\"cellHealth3\" }}";

		Gson gson = new Gson();
		String cellJsonStr = BeanUtils.jsonObjectToJsonArray(sss);
		System.out.println(sss);
		System.out.println(cellJsonStr);
		if (action.equals("showAllLockers")) {

			System.out.println(s);
			System.out.println(ss);
			response.getWriter().write(ss);
		} else if (action.equals("updateCells")) {
			String lockerID = request.getParameter("lockerID");
			// cellJsonStr = updateLocker.showAllCellsInLocker(lockerID);

			// response.getWriter().write(BeanUtils.jsonObjectToJsonArray(cellJsonStr));
			System.out.println("writted");
			// request.getRequestDispatcher("/updateCell.jsp").forward(request, response);
		} else if (action.equals("deleteLocker")) {
			DaoFactory.getUpdateLockerDaoInstance().deleteLocker(request.getParameter("lockerID"));
		} else if (action.equals("requestCell")) {
			System.out.println("alreay wirte back to ajax");
			response.getWriter().write(cellJsonStr);
		}

	}

}
