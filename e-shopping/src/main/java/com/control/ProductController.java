package com.control;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.DAO.product_dao;
import com.Model.Product_seller;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512, maxRequestSize = 1024 * 1024
		* 512)

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public ProductController() {
		super();
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

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
		
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("upload")) {

			String savePath = "C:\\Users\\Charmi Shah\\eclipse-workspace\\e-shopping\\src\\main\\webapp\\images";
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			Part file1 = request.getPart("pimage");
			String fileName = extractfilename(file1);
			file1.write(savePath + File.separator + fileName);
			String filePath = savePath + File.separator + fileName;

			String savePath2 = "C:\\Users\\Charmi Shah\\eclipse-workspace\\e-shopping\\src\\main\\webapp\\images";
			File imgSaveDir = new File(savePath2);
			if (!imgSaveDir.exists()) {
				imgSaveDir.mkdir();
			}
			Product_seller pr = new Product_seller();
			pr.setSid(Integer.parseInt(request.getParameter("sid")));
			pr.setPimage(fileName);
			pr.setPname(request.getParameter("pname"));
			pr.setPcost(request.getParameter("pcost"));
			pr.setPdesc(request.getParameter("pdesc"));
			System.out.println(pr);
			product_dao.insertProduct(pr);
			response.sendRedirect("seller_index.jsp");

		}
		if(action.equalsIgnoreCase("edit")) {
			int pid=Integer.parseInt(request.getParameter("pid"));
			Product_seller pr=product_dao.getProductbyId(pid);
			request.setAttribute("msg", pr);
			request.getRequestDispatcher("product_update.jsp").forward(request, response);
			
			
		}
		if(action.equalsIgnoreCase("update")) {
			String savePath = "C:\\Users\\Charmi Shah\\eclipse-workspace\\e-shopping\\src\\main\\webapp\\images";
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			Part file1 = request.getPart("pimage");
			String fileName = extractfilename(file1);
			file1.write(savePath + File.separator + fileName);
			String filePath = savePath + File.separator + fileName;

			String savePath2 = "C:\\Users\\Charmi Shah\\eclipse-workspace\\e-shopping\\src\\main\\webapp\\images";
			File imgSaveDir = new File(savePath2);
			if (!imgSaveDir.exists()) {
				imgSaveDir.mkdir();
			}
			
			Product_seller pr=new Product_seller();
			int pid=Integer.parseInt(request.getParameter("pid"));
			pr.setPid(pid);
			pr.setSid(Integer.parseInt(request.getParameter("sid")));
			pr.setPimage(fileName);
			pr.setPname(request.getParameter("pname"));
			pr.setPcost(request.getParameter("pcost"));
			pr.setPdesc(request.getParameter("pdesc"));
			
			product_dao.updateProduct(pr);;
			response.sendRedirect("seller_index.jsp");
			
		}
		if(action.equalsIgnoreCase("delete")) {
		
			int pid=Integer.parseInt(request.getParameter("pid"));
			product_dao.deleteProduct(pid);
			response.sendRedirect("manageproduct.jsp");
			
			
		}
		
		
	}
	

}
