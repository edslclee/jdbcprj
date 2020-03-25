package com.ajnet.examle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class update {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String user = "newlec";
		String password = "630810";
		Connection conn =null;
		PreparedStatement st =null;
		
		//int id = Integer.parseInt(request.getParameter("id"));
		//string title = request.getParameter("TITLE");
		//string write_id = request.getParameter("WRITER_ID");
		//string content = request.getParameter("CONTENT");
		//string regdate = request.getParameter("REGDATE"),
		
		int id = 4;
		String title = "BIG Data 최신 정보";
		String content = "BIG Data의 개요, 구축방법, 운영 등에 대한 소개";
		String sql = "UPDATE NOTICE SET TITLE=?, CONTENT=? WHERE ID=?";
		    
		try {
			// 3. JDBC 드라이버 로딩
			Class.forName(driver);
			// 4. 접속
            // - Connection 객체 생성 + 접속 작업.
			
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn.isClosed()?"접속종료":"접속중");// 접속중(false), 접속종료(true)
			 
			// 5. SQL
			
			st = conn.prepareStatement(sql);
			
			st.setString(1, title);
			st.setString(2, content);
			st.setInt(3, id);
			
			// ResultSet rs = null;
			int r = st.executeUpdate();
			System.out.println("변경된 row : " + r);
		
		
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
			
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
			
		} finally {
			
			if(st!=null) {
				   try {
					   st.close();
				   } catch (SQLException e) {
				    	 e.printStackTrace(); 
				   }
			}
			
			if(conn!=null) {
				   try {
				      conn.close();
				     } catch (SQLException e) {
				    	 e.printStackTrace();
				   }
			}
			
		}
		
	}

}
