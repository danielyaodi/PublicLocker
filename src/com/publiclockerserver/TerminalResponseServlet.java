package com.publiclockerserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/TerminalResponseServlet")
public class TerminalResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TerminalResponseServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codeStr = BeanUtils.servletRequestReader(request.getInputStream());
		Gson gson = new Gson();
		TerminalCode_VO terminalCode = gson.fromJson(codeStr, TerminalCode_VO.class);
		if (BeanUtils.checkCode(terminalCode.getCellID(), terminalCode.getCode()))
			;

	}

}
