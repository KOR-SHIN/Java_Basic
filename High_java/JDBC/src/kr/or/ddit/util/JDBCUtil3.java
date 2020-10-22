package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 2) ResourceBundle을 사용한다.
 */
public class JDBCUtil3 {
	static ResourceBundle bundle; // Properties객체 변수 선언
	
	static {
		
		try {
			bundle = ResourceBundle.getBundle("db");
			Class.forName(bundle.getString("driver"));
		} catch(ClassNotFoundException e) {
			System.err.println("Driver 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"), bundle.getString("pass"));
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
