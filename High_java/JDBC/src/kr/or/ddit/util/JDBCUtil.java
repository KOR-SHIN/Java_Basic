package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC드라이버를 로딩하고 Connection객체를 생성하는 메서드를 제공하는 클래스
 */
public class JDBCUtil {
	
	static {
		// Driver loading을 초기화 블럭으로 수행
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("[System Log] : Driver 로딩성공");
		} catch (ClassNotFoundException e) {
			System.out.println("[System Log] : Driver 로딩실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "SKJ", "java");
		} catch(SQLException e ) {
			System.out.println("[System Log] : Connection 실패");
			return null;
		}
	}
	
	public static void disConnect(Connection conn, ResultSet rs, PreparedStatement pstmt, Statement stmt) {
		try{
			if(rs != null) {
				//객체가 생성되지 않았다면 close()를 할 필요가 없다.
				rs.close();
			}
			
			if(stmt != null) {
				stmt.close();
			}
			
			if(conn != null) {
				conn.close();
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("자원반환 실패");
		}
	}
}
