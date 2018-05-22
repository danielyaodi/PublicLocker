package com.publiclockerserver.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.publiclockerserver.*;
import com.publiclockerserver.utils.*;

@WebFilter("/APIKeyFilter")
public class APIKeyFilter implements Filter {

	public APIKeyFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter");

		String requestStr = BeanUtils.servletRequestReader(request.getInputStream());
		System.out.println("22" + requestStr);

		if (!requestStr.equals(null)&&(requestStr=="")) {

			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(requestStr).getAsJsonObject();
			String apiKey = json.get("apiKey").toString();

			if (BeanUtils.checkAPIKey(apiKey)) {

				chain.doFilter(request, response);

			} else {
				String error = "{\"error\":\"" + Constant.API_KEY_VERIFICATION_FAILED + "\"}";

				response.getWriter().write(error);
			}

		} else {
			String error = "{\"error\":\"" + Constant.API_KEY_VERIFICATION_FAILED + "\"}";

			response.getWriter().write(error);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
