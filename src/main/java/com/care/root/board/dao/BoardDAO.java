package com.care.root.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.care.root.board.dto.BoardDTO;


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
	
	public ArrayList<BoardDTO> list(){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
//		String sql = "select * from test_board";		
		String sql = "select * from test_board order by idgroup desc, step asc";
		try {
			ps = con.prepareStatement(sql);
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
	
}












