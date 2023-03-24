package com.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.customerdao;
import com.DAO.sellerdao;
import com.Model.Customer_reg;
import com.Model.Seller_reg;

/**
 * Servlet implementation class Actioncontrol_customerreg
 */
@WebServlet("/Actioncontrol_customerreg")
public class Actioncontrol_customerreg extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("SEND")) {
			Customer_reg cr=new Customer_reg();
			cr.setName(request.getParameter("name"));
			cr.setEmail(request.getParameter("email"));
			cr.setPassword(request.getParameter("password"));
			cr.setMobileno(request.getParameter("mobilenumber"));
			cr.setAddress(request.getParameter("address"));
			cr.setPincode(request.getParameter("pincode"));
			
			System.out.println(request.getParameter("name"));
			System.out.println(request.getParameter("email"));
			System.out.println(request.getParameter("password"));
			System.out.println(request.getParameter("mobilenumber"));
			System.out.println(request.getParameter("address"));
			System.out.println(request.getParameter("pincode"));
			
			customerdao.insertData(cr);
			response.sendRedirect("customer_index.jsp");
			
			
		}
		if(action.equalsIgnoreCase("login")) {
			Customer_reg cr = new Customer_reg();
			cr.setEmail(request.getParameter("email"));
			cr.setPassword(request.getParameter("password"));
			String em=request.getParameter("email");
			String pas=request.getParameter("password");
			System.out.println(cr);
			Customer_reg cr1=customerdao.checklogin(cr);
			
			if(em.equals(null) || pas.equals(null)) {
				request.setAttribute("msg", "all feilds are mandatory");
				request.getRequestDispatcher("customer-login.jsp");
				
				
			}
			else if(cr1==null) {
				request.setAttribute("msg", "email or password is incorrect");
				request.getRequestDispatcher("customer-login.jsp");
				
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("msg", cr1);
				request.getRequestDispatcher("customer_index.jsp").forward(request, response);
			}
		}
		if(action.equalsIgnoreCase("update")) {
			
			Customer_reg cr=new Customer_reg();
			int id=Integer.parseInt(request.getParameter("id"));
			cr.setId(id);
			cr.setName(request.getParameter("name"));
			cr.setEmail(request.getParameter("email"));
			cr.setPassword(request.getParameter("password"));
			cr.setMobileno(request.getParameter("mobilenumber"));
			cr.setAddress(request.getParameter("address"));
			cr.setPincode(request.getParameter("pincode"));
			customerdao.updateCustomer(cr);
			response.sendRedirect("customer_index.jsp");
		}
		
		
	}

}
