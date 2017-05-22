package com.lgu.loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	Connection			conn	= null;
	Statement			stmt	= null;
	ResultSet			rset	= null;

	public DbConnection() {
	}

	public boolean openConnection() {

		DbConfig dbInfo = DbConfig.getInstance();
		try {
			Class.forName(dbInfo.getDriver());
			conn = DriverManager.getConnection(dbInfo.getUrl(), dbInfo.getUserName(), dbInfo.getPassWord());
		} catch (SQLException e) {
			System.out.println("Connection Failed: "+e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public ResultSet executeQuery(String query) throws SQLException {
		this.stmt = conn.createStatement();
		this.rset = stmt.executeQuery(query);
		return rset;
	}

	public void close() throws SQLException {
		if(conn!=null) conn.close();
		if(rset!=null) rset.close();
		if(stmt!=null) stmt.close();
	}
	
	
}