package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import kr.or.ddit.util.JDBCUtil;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	    ---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128)    -- 주소
);

*/
public class T02_MemberInfoTest {
	
	// Log4j를 이용한 log를 남기기 위한 logger 생성
	private static final Logger sqlLogger = Logger.getLogger("log4jExam.sql.query");
	private static final Logger paramLogger = Logger.getLogger("log4jExam.sql.param");
	
	// class를 이용해서 logger생성
	private static final Logger resultLogger = Logger.getLogger(T02_MemberInfoTest.class);
	
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayMemberAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	/**
	 * 회원정보를 삭제하는 메서드
	 * (입력받은 회원 ID를 이용하여 삭제)
	 */
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원 ID >> ");

		String memId = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				System.out.println(memId + "회원 삭제완료");
			} else {
				System.out.println(memId + "회원 삭제실패");
			}
			
		} catch(SQLException e) { 
			System.out.println(memId + "회원 삭제실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, rs, pstmt, stmt);
		}
	}

	/**
	 * 회원정보를 수정하는 메서드
	 */
	private void updateMember() {
		boolean check = false; // 기존회원 존재여부 체크
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요.");
			System.out.print("회원 ID >> ");

			memId = scan.next();
			
			check = getMember(memId);
			
			if(!check) {
				System.out.println(memId + "인 회원이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		} while(check == false);
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		scan.nextLine();
		
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " UPDATE MYMEMBER "
						+ "   SET MEM_NAME = ? "
						+ "      ,MEM_TEL = ? "
						+ "      ,MEM_ADDR = ? "
						+ "WHERE MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				System.out.println(memId + "회원의 정보를 수정했습니다.");
			} else {
				System.out.println(memId + "회원의 정보를 수정실패.");
			}

		} catch(SQLException e) {
			System.out.println(memId + "회원의 정보를 수정실패.");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, rs, pstmt, stmt);
		}
	}

	/**
	 * 전체회원 리스트를 화면에 표시해주는 메서드
	 */
	private void displayMemberAll() {
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "SELECT * FROM MYMEMBER";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println();
			System.out.println("----------------------------------------------------------------");
			System.out.println(" ID\t이름\t전화번호\t\t주  소");
			System.out.println("----------------------------------------------------------------");

			while(rs.next()) {
				String memId =  rs.getString("MEM_ID");
				String memName =  rs.getString("MEM_Name");
				String memTel =  rs.getString("MEM_Tel");
				String memAddr =  rs.getString("MEM_Addr");
				System.out.println(memId + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			System.out.println("----------------------------------------------------------------");
			System.out.println("출력완료");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, rs, pstmt, pstmt);
		}
		
	}

	private void insertMember() {

		boolean check = false; // 기존회원 존재여부 체크
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.print("회원 ID >> ");

			memId = scan.next();
			
			check = getMember(memId);
			
			if(check) {
				System.out.println("회원 ID가 " + memId + "인 회원은 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		} while(check == true);
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		scan.nextLine();
		
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "INSERT INTO MYMEMBER "
				 	 + " (MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR) "
					 + " VALUES(?, ?, ?, ?)";
			
			sqlLogger.debug("query : " + sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			paramLogger.debug("param : ( " + memId + ", " + memName + ", " + memTel + ", " + memAddr + " )");
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + " 회원 가입 성공");
			} else {
				System.out.println(memId + " 회원 가입 실패");
			}
			
			resultLogger.info("result : " + cnt);
			
		} catch(SQLException e) {
			System.out.println(memId + " 회원 가입 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, rs, pstmt, pstmt);
		}
	}

	/**
	 * 회원 ID를 이용하여 가입여부를 확인하는 메서드
	 * SQL injection 공격을 방어하기위해 PreparedStatement를 사용한다.
	 * PreparedStatement와 Statement 차이점, SQL injection개념 찾아보기
	 * @param memId
	 * @return
	 */
	private boolean getMember(String memId) {
		
		boolean check = false;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT COUNT(*) AS CNT "
						+ " FROM MYMEMBER "
						+ "WHERE MEM_ID = ? "; // WildCard를 사용하는건 PreparedStatement를 사용하려는 것
			
			// 미리 만들어놓은 Query를 사용하여 preparedStatement 생성
			pstmt = conn.prepareStatement(sql);
			
			// pstmt.setString(물음표 인덱스(1부터 시작), column명); 
			pstmt.setString(1, memId);
			
			// Query 실행
			rs = pstmt.executeQuery();
			
			int count = 0;
			if(rs.next()) {
				count = rs.getInt("CNT");
			}
			
			if(count > 0) {
				check = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			check = false;
		} finally {
			JDBCUtil.disConnect(conn, rs, pstmt, stmt);
		}
		
		return check;
	}

	public static void main(String[] args) {
		T02_MemberInfoTest memObj = new T02_MemberInfoTest();
		memObj.start();
	}

}






