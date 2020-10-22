package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC를 이용한 데이터베이스 처리 순서
 * JDBC드라이버 로딩 -> 해당 DB접속 -> 질의(SQL 명령수행)
 * -> 질의 결과를 받아서 처리 -> 종료(자원반납) 
 *
 * <driverLoading>
 * JDBC 드라이버는 DB를 만든 회사에서 제공한다.
 * Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * <Connetion>
 * 접속이 성공하면 Connection 객체가 생성된다.
 * DriverManager.getConnection()
 * 
 * <Query>
 * Statement객체 또는 PreparedStatement객체를 이용하여
 * SQL문장을 실행한다.
 * 
 * <Result>
 * SQL문이 select일 경우 => ResultSet객체가 생성된다.
 * ResultSet객체에는 select문 결과가 저장된다.
 * 
 * SQL문이 update, delete일 경우 => 정수값을 반환한다.
 * (정수값은 보통 실행에 성공한 레코드 수를 의미한다)
 */
public class T01_JdbcTest {

	private final String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private final String id = "SKJ";
	private final String pass = "java";
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	
	public void query() {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			// Driver loading
			Class.forName(driver);
			
			// Connection Object 
			con = DriverManager.getConnection(url, id, pass);
			
			stmt = con.createStatement();
			
			String sql = "SELECT * FROM LPROD";
			
			// execute Query
			rs = stmt.executeQuery(sql);
			
			System.out.println("실행한 쿼리문 : " + sql);
			System.out.println("=====[쿼리문 실행결과]=====");
			while(rs.next()) {
				// print ResultSet
				System.out.println("LPROD ID : " + rs.getString("LPROD_ID"));
				System.out.println("LPROD GU : " + rs.getString("LPROD_GU"));
				System.out.println("LPROD NM : " + rs.getString("LPROD_NM"));
				System.out.println("--------------------------------------");
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try{
				if(rs != null) {
					//객체가 생성되지 않았다면 close()를 할 필요가 없다.
					rs.close();
				}
				
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("자원반환 실패");
			}
		}
	} 
}
