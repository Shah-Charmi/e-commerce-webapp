package com.control;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.DAO.sellerdao;
import com.Model.Seller_reg;

/**
 * Servlet implementation class Actioncontrol
 */
@WebServlet("/Actioncontrol")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512, maxRequestSize = 1024 * 1024
		* 512)

public class Actioncontrol extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private String extractfilename(Part file) {
		String cd = file.getHeader("content-disposition");
		System.out.println(cd);
		String[] items = cd.split(";");
		for (String string : items) {
			if (string.trim().startsWith("filename")) {
				return string.substring(string.indexOf("=") + 2, string.length() - 1);
			}
		}
		return "";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("SEND")) {
			String savePath = "C:\\Users\\Charmi Shah\\eclipse-workspace\\e-shopping\\src\\main\\webapp\\images";
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			Part file1 = request.getPart("image");
			String fileName = extractfilename(file1);
			file1.write(savePath + File.separator + fileName);
			String filePath = savePath + File.separator + fileName;

			String savePath2 = "C:\\Users\\Charmi Shah\\eclipse-workspace\\e-shopping\\src\\main\\webapp\\images";
			File imgSaveDir = new File(savePath2);
			if (!imgSaveDir.exists()) {
				imgSaveDir.mkdir();
			}
			Seller_reg sr = new Seller_reg();
			sr.setImage(fileName);
			sr.setName(request.getParameter("name"));
			sr.setEmail(request.getParameter("email"));
			sr.setPassword(request.getParameter("password"));
			sr.setMobileno(request.getParameter("mobilenumber"));
			sr.setAddress(request.getParameter("address"));
			sr.setGstnumber(request.getParameter("gstnumber"));

			System.out.println(request.getParameter("name"));
			System.out.println(request.getParameter("email"));
			System.out.println(request.getParameter("password"));
			System.out.println(request.getParameter("mobilenumber"));
			System.out.println(request.getParameter("address"));
			System.out.println(request.getParameter("gstnumber"));

			sellerdao.insertData(sr);
			response.sendRedirect("seller_index.jsp");

		}
		if (action.equalsIgnoreCase("Login")) {
			Seller_reg sr = new Seller_reg();
			sr.setEmail(request.getParameter("email"));
			sr.setPassword(request.getParameter("password"));
			String em = request.getParameter("email");
			String pas = request.getParameter("password");
			System.out.println(sr);
			Seller_reg sr1 = sellerdao.checklogin(sr);
			if (em.equals(null) || pas.equals(null)) {
				request.setAttribute("msg", "all feilds are mandatory");
				request.getRequestDispatcher("seller-login.jsp");

			} else if (sr1 == null) {
				request.setAttribute("msg", "email or password is incorrect");
				request.getRequestDispatcher("seller-login.jsp");

			} else {
				HttpSession session = request.getSession();
				session.setAttribute("msg", sr1);
				request.getRequestDispatcher("seller_index.jsp").forward(request, response);

			}
		}

		if (action.equalsIgnoreCase("update")) {
			String savePath = "C:\\Users\\Charmi Shah\\eclipse-workspace\\e-shopping\\src\\main\\webapp\\images";
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			Part file1 = request.getPart("image");
			String fileName = extractfilename(file1);
			System.out.println();
			file1.write(savePath + File.separator + fileName);
			String filePath = savePath + File.separator + fileName;

			String savePath2 = "C:\\Users\\Charmi Shah\\eclipse-workspace\\e-shopping\\src\\main\\webapp\\images";
			File imgSaveDir = new File(savePath2);
			if (!imgSaveDir.exists()) {
				imgSaveDir.mkdir();
			}

			Seller_reg sr = new Seller_reg();
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			sr.setId(id);
			sr.setImage(fileName);
			sr.setName(request.getParameter("name"));
			sr.setEmail(request.getParameter("email"));
			sr.setPassword(request.getParameter("password"));
			sr.setMobileno(request.getParameter("mobilenumber"));
			sr.setAddress(request.getParameter("address"));
			sr.setGstnumber(request.getParameter("gstnumber"));
			sellerdao.UpdateSeller(sr);
			response.sendRedirect("seller_index.jsp");

		}
		

		if (action.equalsIgnoreCase("logout")) {
			HttpSession session = request.getSession();
			session.getAttribute("sr");
			session.invalidate();
			response.sendRedirect("seller-login.jsp");
		}
	}

}
