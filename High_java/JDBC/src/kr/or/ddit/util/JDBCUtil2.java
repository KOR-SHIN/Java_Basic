package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 1) Properties객체 이용하기
 */
public class JDBCUtil2 {
	static Properties prop; // Properties객체 변수 선언
	
	static {
		prop = new Properties();
		
		File file = new File("res/db.properties");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
			
			Class.forName(prop.getProperty("driver"));
		} catch(IOException e) {
			System.err.println("File이 없거나 IOException이 발생하였습니다.");
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			System.err.println("Driver 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pass"));
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
