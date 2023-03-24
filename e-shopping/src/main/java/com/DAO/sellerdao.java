package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Model.Seller_reg;
import com.Util.dbconnection;

public class sellerdao {
	public static void insertData(Seller_reg sr) {
		try {
			Connection conn = dbconnection.createConnection();
			String sql = "insert into data_seller(image,name,email,password,mobilenumber,address,gstnumber) values(?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, sr.getImage());
			pst.setString(2, sr.getName());
			pst.setString(3, sr.getEmail());
			pst.setString(4, sr.getPassword());
			pst.setString(5, sr.getMobileno());
			pst.setString(6, sr.getAddress());
			pst.setString(7, sr.getGstnumber());
			pst.executeUpdate();
			System.out.println("data inserted");
		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
		}
                              
	}
	public static Seller_reg checklogin(Seller_reg sr) {
		Seller_reg sr1=null;
		try {
			Connection conn = dbconnection.createConnection();
			String sql = "select * from data_seller where email=? and password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, sr.getEmail());
			pst.setString(2, sr.getPassword());
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				sr1=new Seller_reg();
				sr1.setId(rs.getInt("id"));
				sr1.setImage(rs.getString("image"));
				sr1.setName(rs.getString("name"));
				sr1.setEmail(rs.getString("email"));
				sr1.setPassword(rs.getString("password"));
				sr1.setMobileno(rs.getString("mobilenumber"));
				sr1.setAddress(rs.getString("address"));
				sr1.setGstnumber(rs.getString("gstnumber"));
				System.out.println("successfully logged in!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sr1;
	}
	public static Seller_reg getsellerbyId(int id) {
		Seller_reg sr=null;
		try {
			Connection conn = dbconnection.createConnection();
			String sql = "select * from data_seller where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				sr=new Seller_reg();
				sr.setId(rs.getInt("id"));
				sr.setImage(rs.getString("image"));
				sr.setName(rs.getString("name"));
				sr.setEmail(rs.getString("email"));
				sr.setPassword(rs.getString("password"));
				sr.setMobileno(rs.getString("mobilenumber"));
				sr.setAddress(rs.getString("address"));
				sr.setGstnumber(rs.getString("gstnumber"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sr;
	}
	public static void UpdateSeller(Seller_reg sr) {
		try {
			Connection conn=dbconnection.createConnection();
			String sql="update data_seller set image=?,name=?,email=?,password=?,mobilenumber=?,address=?,gstnumber=? where id=?";
		    PreparedStatement pst=conn.prepareStatement(sql);
		    pst.setString(1, sr.getImage());
		    pst.setString(2, sr.getName());
			pst.setString(3, sr.getEmail());
			pst.setString(4, sr.getPassword());
			pst.setString(5, sr.getMobileno());
			pst.setString(6, sr.getAddress());
			pst.setString(7, sr.getGstnumber());
			pst.setInt(8, sr.getId() );
			pst.executeUpdate();
			System.out.println("data updated");
		    
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}
   
