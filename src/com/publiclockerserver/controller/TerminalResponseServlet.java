package com.publiclockerserver.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.publiclockerserver.DaoFactory;
import com.publiclockerserver.pojo.TerminalCode_VO;
import com.publiclockerserver.utils.BeanUtils;

@WebServlet("/TerminalResponseServlet")
public class TerminalResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TerminalResponseServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("terminalresponse");
	}
	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codeStr = BeanUtils.servletRequestReader(request.getInputStream());
		Gson gson = new Gson();
		TerminalCode_VO terminalCode = gson.fromJson(codeStr, TerminalCode_VO.class);
		int codeType = BeanUtils.checkCode(terminalCode.getCellID(), terminalCode.getCode());
		if (codeType == 1) { // ==deliveryCode
			DaoFactory.getCodeProcessDaoInstance().deliveryCode(terminalCode.getCellID());
			response.getOutputStream().write(("door opened").getBytes());

		} else if (codeType == 2) { // ==pickupCode
			DaoFactory.getCodeProcessDaoInstance().pickupCode(terminalCode.getCellID());
			response.getOutputStream().write(("door opened").getBytes());
		} else {
			response.getOutputStream().write(("invalid").getBytes());
		}

	}

}
