package com.publiclockerserver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddressQueryServlet
 */
@WebServlet("/AddressQueryServlet")
public class LockerRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LockerRequestServlet() {
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
		
		
		
		
		
		String apiKey=null;
		if(new CustomerVerifyDaoImpl().apiKeyVerify(apiKey)) {
			
			//  call addressQuery(apiKey,orderNumber,packageType,packageQty,zipcode)
			//  get Map {
			//				{cellID:   }
			//				{address:	}
			//				{timeCommitted:  }
			//			}
			// convert to json send to client;
			
			
			
		}
		
		
		
		
	}

}
