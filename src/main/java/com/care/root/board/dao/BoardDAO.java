package com.care.root.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.care.root.board.dto.BoardDTO;
import com.care.root.paging.PageCount;


public class BoardDAO {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public BoardDAO() throws SQLException {		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			con = DriverManager.getConnection(url, "raina", "5598");
			System.out.println(" 연결 성공!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BoardDTO> list(int start, int end){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
//		String sql = "select * from test_board";		
//		String sql = "select * from test_board order by idgroup desc, step asc";
		String sql=
				"select B.* from(select rownum rn, A.* from(select * from test_board "
				+ "order by idgroup desc, step asc)A)B where rn between ? and ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO	d = new BoardDTO();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setTitle(rs.getString("title"));
				d.setSavedate(rs.getTimestamp("savedate"));
				d.setHit(rs.getInt("hit"));
				d.setIdgroup(rs.getInt("idgroup"));
				d.setStep(rs.getInt("step"));
				d.setIndent(rs.getInt("indent"));								
				list.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return list;
	}
	
	public void writeSave(String name, String title, String content) {
		String sql = "insert into test_board(id, name, title, content, idgroup, step, indent) "
				+ "values(test_board_seq.nextval, ?, ?, ?, test_board_seq.currval, 0, 0)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}		
	
	private void upHit(String id) {
		String sql = "update test_board set hit = hit +1 where id="+id;
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
																		 
	public BoardDTO contentView(String id, int flag) { 
		if(flag ==1) {
			upHit(id);
		}
		BoardDTO d = new BoardDTO();
		String sql  ="select * from test_board where id =? ";  
	// String sql = "select * from text_board where id =" +id; //작은 따옴표는 문자일때만 사용		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setTitle(rs.getString("title"));
				d.setContent(rs.getString("content"));
				d.setSavedate(rs.getTimestamp("savedate"));
				d.setHit(rs.getInt("hit"));
				d.setIdgroup(rs.getInt("idgroup"));
				d.setStep(rs.getInt("step"));
				d.setIndent(rs.getInt("indent"));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}						
		return d;
	}
	
	public void modify(String id, String name, String title, String content) {
		String sql = "update test_board set name=?, title=?, content=? where id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.setString(4, id);
			ps.executeUpdate();
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	public void delete(String id) {
		String sql = "delete from test_board where id="+id;
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void replyShape(BoardDTO dto) {
		String sql = "update test_board set step=step+1 where idgroup=? and step>?";//step이 0보다 큰거 
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getIdgroup());
			ps.setInt(2, dto.getStep());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reply(BoardDTO dto) {
		replyShape(dto); 	//step(댓글위치)을 조정해줌  //indent는 댓글인지 대댓글인지 알기위함 
		String sql="insert into test_board(id, name, title, content, idgroup, step, indent) "
				+ "values(test_board_seq.nextval, ?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setInt(4, dto.getIdgroup());
			ps.setInt(5, dto.getStep()+1); //댓글들에 대한 위치 . 정렬 시 
			ps.setInt(6, dto.getIndent()+1);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	public int getTotalPage() {
		String sql = "select count(*) from test_board";
		int totPage=0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				totPage = rs.getInt(1); //1을 넣어야 나온다 한다..0넣으면 안나온다..궁금하면 알아보기
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return totPage;
	}
	
	public PageCount pagingNum(int start) {
		PageCount pc = new PageCount();
		if(start == 0) {
			start = 1;
		}
		int pageNum = 3;
		int totalPage = getTotalPage();
		
		int totEndPage = totalPage / pageNum;
		if( totalPage % pageNum != 0) {
			totEndPage ++;
		}
		
		int endPage = start * pageNum;
		int startPage = endPage + 1 -pageNum;
		
		pc.setStartPage(startPage); //페이지별 첫번째 게시물 번호
		pc.setEndPage(endPage); //페이지별 마지막 게시물 번호 ??
		pc.setTotEndPage(totEndPage); //페이징 마지막 페이지 번호
		
		return pc;
	}
}












