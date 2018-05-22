package com.publiclockerserver.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
		DispatcherType.ERROR }, urlPatterns = { "/AdminPageFilter", "/admin.jsp" })
public class AdminPageFilter implements Filter {

	public AdminPageFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String ip = request.getRemoteAddr();
		if (ip.equals("127.0.0.1")) {

			chain.doFilter(request, response);
			System.out.println("adminpageFilter checked");
			
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
