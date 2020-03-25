package com.ajnet.examle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class selectby {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String user = "newlec";
		String password = "630810";
		//int id = Integer.parseInt(request.getParameter("id")); 
		int id = 3;
		
		try {
			// 3. JDBC 드라이버 로딩
			Class.forName(driver);
			// 4. 접속
            // - Connection 객체 생성 + 접속 작업.
			Connection conn =null;
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn.isClosed()?"접속종료":"접속중");// 접속중(false), 접속종료(true)
			 
			// 5. SQL
			String sql = "SELECT * FROM NOTICE WHERE ID=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = null;
			rs = st.executeQuery();
			
			rs.next();
			
			System.out.printf("ID :%d, TITLE : %s, WRITER_ID : %s, CONTENT: %s,REGDATE: %s, HIT: %d", rs.getInt("ID"), rs.getString("TITLE"), rs.getString("WRITER_ID"), rs.getString("CONTENT"), rs.getDate("REGDATE"),rs.getInt("HIT"));
			System.out.println();
						
			 // 6. 접속종료
			if (rs!=null) {
				try {
				      rs.close();
				    } catch (SQLException e) {
				  }
			}
			
			if(st!=null) {
				   try {
				      st.close();
				     } catch (SQLException e) {
				   }
			}
			
			if(conn!=null) {
				   try {
				      conn.close();
				     } catch (SQLException e) {
				   }
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}
		
	}

}
