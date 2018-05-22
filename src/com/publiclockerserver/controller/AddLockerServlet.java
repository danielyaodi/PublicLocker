package com.publiclockerserver.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.publiclockerserver.DaoFactory;
import com.publiclockerserver.pojo.AddLocker_VO;
import com.publiclockerserver.utils.BeanUtils;

@WebServlet("/AddLockerServlet")
public class AddLockerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String orderConfirmStr = BeanUtils.servletRequestReader(request.getInputStream()); // read all json file from
																							// request.
		Gson gson = new Gson();
		AddLocker_VO addLocker = gson.fromJson(orderConfirmStr, AddLocker_VO.class); // convert json string to
																						// AddLocker_VO obj
		DaoFactory.getAddLockerDaoInstance(addLocker).addLocker(); // create new LockerID and cellID, insert to
																	// database;

	}

}
