package com.ajnet.examle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class select {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String user = "newlec";
		String password = "630810";
		
		try {
			// 3. JDBC 드라이버 로딩
			Class.forName(driver);
			// 4. 접속
            // - Connection 객체 생성 + 접속 작업.
			Connection conn =null;
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn.isClosed()?"접속종료":"접속중");// 접속중(false), 접속종료(true)
			 
			// 5. SQL
			String sql = "SELECT * FROM NOTICE ORDER BY ID DESC";
			Statement st = conn.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(sql);
			
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
