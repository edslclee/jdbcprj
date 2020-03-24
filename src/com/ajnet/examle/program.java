package com.ajnet.examle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		//String sql = "SELECT * FROM NOTICE;
		String sql = "SELECT * FROM NOTICE WHERE HIT >=10";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "newlec", "630810");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		
		while (rs.next()) {
			
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writer_id = rs.getString("WRITER_ID");
			String content = rs.getString("CONTENT");
			Date regdate = rs.getDate("REGDATE");
			int hit = rs.getInt("HIT");
			
			System.out.printf("ID :%d, TITLE : %s, WRITER_ID : %s, CONTENT: %s,REGDATE: %s, HIT: %d",
					id, title, writer_id, content, regdate, hit);
			System.out.println();
		}
		
		rs.close();
		st.close();
		con.close();
	}

}
