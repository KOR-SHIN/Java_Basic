package kr.or.ddit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "INSERT INTO MYMEMBER " + " (MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR) " + " VALUES(?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_id());
			pstmt.setString(2, mv.getMem_name());
			pstmt.setString(3, mv.getMem_tel());
			pstmt.setString(4, mv.getMem_addr());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, rs, pstmt, pstmt);
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String mem_id) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, rs, pstmt, stmt);
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();

			String sql = " UPDATE MYMEMBER " + "   SET MEM_NAME = ? " + ",MEM_TEL = ? " + ",MEM_ADDR = ? "
					+ "WHERE MEM_ID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_name());
			pstmt.setString(2, mv.getMem_tel());
			pstmt.setString(3, mv.getMem_addr());
			pstmt.setString(4, mv.getMem_id());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, rs, pstmt, stmt);
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> displayAllMember() {
		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * FROM MYMEMBER";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setMem_id(rs.getString("MEM_ID"));
				member.setMem_name(rs.getString("MEM_Name"));
				member.setMem_tel(rs.getString("MEM_TEL"));
				member.setMem_addr(rs.getString("MEM_Addr"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, rs, pstmt, pstmt);
		}
		
		return memberList;
	}

	@Override
	public boolean getMember(String mem_id) {
		boolean check = false;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT COUNT(*) AS CNT " + " FROM MYMEMBER " + "WHERE MEM_ID = ? "; // WildCard를 사용하는건
																							  // PreparedStatement를
																							  // 사용하려는 것
			// 미리 만들어놓은 Query를 사용하여 preparedStatement 생성
			pstmt = conn.prepareStatement(sql);

			// pstmt.setString(물음표 인덱스(1부터 시작), column명);
			pstmt.setString(1, mem_id);

			// Query 실행
			rs = pstmt.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt("CNT");
			}

			if (count > 0) {
				check = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			check = false;
		} finally {
			JDBCUtil.disConnect(conn, rs, pstmt, stmt);
		}

		return check;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		List<MemberVO> memberList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			
			// dynamic query
			// WHERE 1=1이 붙은 이유는 뒤에 AND를 붙이기 위해서이다.
			String sql = "SELECT * "
					    + " FROM MYMEMBER "
					    + "WHERE 1=1 ";
			
			if(mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				sql += "AND MEM_ID = ? ";
			}

			if(mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				sql += "AND MEM_NAME = ? ";
			}

			if(mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				sql += "AND MEM_TEL = ? ";
			}
			
			if(mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				sql += "AND MEM_ADDR LIKE '%' || ? || '%' ";
			}
			pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			
			if(mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				pstmt.setString(idx++, mv.getMem_id());
			}
			
			if(mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				pstmt.setString(idx++, mv.getMem_name());
			}

			if(mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				pstmt.setString(idx++, mv.getMem_tel());
			}
			
			if(mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				pstmt.setString(idx++, mv.getMem_addr());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMem_addr(rs.getString("MEM_ADDR"));
				member.setMem_id(rs.getString("MEM_ID"));
				member.setMem_name(rs.getString("MEM_NAME"));
				member.setMem_tel(rs.getString("MEM_TEL"));
				memberList.add(member);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, rs, pstmt, pstmt);
		}
		
		return memberList;
	}

}
