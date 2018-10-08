package com.maker.store.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

public class DbUtil {
	public static Connection getConnection(){
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/maker","root","123456");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public static boolean executeUpdate(String sql,Object[] args){
		Connection conn=null;
		PreparedStatement pst=null;
		int rowsCount=0;
		try{
			conn=getConnection();
			pst=conn.prepareStatement(sql);
			if(args!=null&args.length>0){
				for(int i=0;i<args.length;i++){
					pst.setObject(i+1, args[i]);
				}
			}
			rowsCount=pst.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rowsCount>0?true:false;
	}
	public static ResultSet executeQuery(String sql,Object[] args){
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			conn=getConnection();
			pst=conn.prepareStatement(sql);
			if(args!=null&args.length>0){
				for(int i=0;i<args.length;i++){
					pst.setObject(i+1, args[i]);
				}
			}
			rs=pst.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
}
