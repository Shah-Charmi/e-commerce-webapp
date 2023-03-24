package com.DAO;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.Model.Customer_reg;
import com.Model.Product_seller;
import com.Util.dbconnection;

public class customerdao {
	public static void insertData(Customer_reg cr) {
		Connection con=null;
		try {
			con=dbconnection.createConnection();
			String sql="insert into data_customer(name,email,password,mobilenumber,address,pincode) values(?,?,?,?,?,?)";
		    PreparedStatement pst=con.prepareStatement(sql);
		    pst.setString(1, cr.getName());
		    pst.setString(2, cr.getEmail());
		    pst.setString(3, cr.getPassword());
		    pst.setString(4, cr.getMobileno());
		    pst.setString(5, cr.getAddress());
		    pst.setString(6, cr.getPincode());
		    pst.executeUpdate();
		    System.out.println("data inserted");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static Customer_reg checklogin(Customer_reg cr) {
		Customer_reg cr1=null;
		try {
			Connection conn = dbconnection.createConnection();
			String sql = "select * from data_customer where email=? and password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,cr.getEmail() );
			pst.setString(2, cr.getPassword());
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				cr1=new Customer_reg();
				cr1.setId(rs.getInt("id"));
				cr1.setName(rs.getString("name"));
				cr1.setEmail(rs.getString("email"));
				cr1.setPassword(rs.getString("password"));
				cr1.setMobileno(rs.getString("mobilenumber"));
				cr1.setAddress(rs.getString("address"));
				cr1.setPincode(rs.getString("pincode"));
				System.out.println("successfully logged in!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cr1;
	}
	
	public static Customer_reg getCustomerbyid(int id) {
		Customer_reg cr=null;
		try {
			Connection conn = dbconnection.createConnection();
			String sql = "select * from data_customer where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				cr=new Customer_reg();
				
				cr.setId(rs.getInt("id"));
				cr.setName(rs.getString("name"));
				cr.setEmail(rs.getString("email"));
				cr.setPassword(rs.getString("password"));
				cr.setMobileno(rs.getString("mobilenumber"));
				cr.setAddress(rs.getString("address"));
				cr.setPincode(rs.getString("pincode"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return cr;
		
		
	}
	public static void updateCustomer(Customer_reg cr) {
		try {
			Connection conn=dbconnection.createConnection();
			String sql="update data_customer set name=?,email=?,password=?,mobilenumber=?,address=?,pincode=? where id=?";
		    PreparedStatement pst=conn.prepareStatement(sql);
		    pst.setString(1, cr.getName());
		    pst.setString(2, cr.getEmail());
		    pst.setString(3, cr.getPassword());
		    pst.setString(4, cr.getMobileno());
		    pst.setString(5, cr.getAddress());
		    pst.setString(6, cr.getPincode());
		    pst.setInt(7, cr.getId());
		    pst.executeUpdate();
		    
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static ArrayList<Product_seller> getallProducts(){
		ArrayList<Product_seller> list=new ArrayList<Product_seller>();
		try {
			Connection conn=dbconnection.createConnection();
			String sql="select * from product_data";
			PreparedStatement pst=conn.prepareStatement(sql);
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
}
   

	
	

   



