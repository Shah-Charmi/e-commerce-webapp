package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Model.Customer_reg;
import com.Model.Product_seller;
import com.Util.dbconnection;

public class product_dao {
	public static void insertProduct(Product_seller pr) {
		try {
			Connection conn=dbconnection.createConnection();
			String sql="insert into product_data(sid,pimage,pname,pcost,pdesc) values(?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, pr.getSid());
			pst.setString(2, pr.getPimage());
			pst.setString(3, pr.getPname());
			pst.setString(4, pr.getPcost());
			pst.setString(5, pr.getPdesc());
			pst.executeUpdate();
			System.out.println("product uploaded");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static List<Product_seller> getProductsbyId(int id){
		ArrayList<Product_seller> list=new ArrayList<Product_seller>();
		
		try {
			
			Connection conn=dbconnection.createConnection();
			String sql="select * from product_data where sid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Product_seller pr=new Product_seller();
				pr.setPid(rs.getInt("pid"));
				pr.setSid(rs.getInt("sid"));
				pr.setPimage(rs.getString("pimage"));
				pr.setPname(rs.getString("pname"));
				pr.setPcost(rs.getString("pcost"));
				pr.setPdesc(rs.getString("pdesc"));
				list.add(pr);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public static Product_seller getProductbyId(int pid) {
		Product_seller pr=null;
		try {
			Connection conn=dbconnection.createConnection();
			String sql="select * from product_data where pid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, pid);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				pr=new Product_seller();
				pr.setPid(rs.getInt("pid"));
				pr.setSid(rs.getInt("sid"));
				pr.setPimage(rs.getString("pimage"));
				pr.setPname(rs.getString("pname"));
				pr.setPcost(rs.getString("pcost"));
				pr.setPdesc(rs.getString("pdesc"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pr;
	}
	public static void updateProduct(Product_seller pr) {
		try {
			Connection conn=dbconnection.createConnection();
			String sql="update product_data set sid=?,pimage=?,pname=?,pcost=?,pdesc=? where pid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, pr.getSid());
			pst.setString(2, pr.getPimage());
			pst.setString(3, pr.getPname());
			pst.setString(4, pr.getPcost());
			pst.setString(5, pr.getPdesc());
			pst.setInt(6, pr.getPid());
			pst.executeUpdate();
			System.out.println("data updated");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void deleteProduct(int pid){
		try {
			Connection conn=dbconnection.createConnection();
			String sql="delete from product_data where pid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, pid);
			System.out.println("data deleted");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
