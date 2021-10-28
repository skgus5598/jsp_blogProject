package com.care.root.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.care.root.member.dto.MemberDTO;

public class MemberDAO {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public MemberDAO() throws Exception {		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			con = DriverManager.getConnection(url, "raina", "5598");
			System.out.println(" 연결 성공!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//모든 유저목록 가져오기 
	public ArrayList<MemberDTO> memberView(){
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
/*		for(int i=0; i<5; i++) {
			MemberDTO d = new MemberDTO();
			d.setId("aaa0"+i);
			d.setPwd("aaa0"+i);
			d.setName("홍길동0"+i);
			d.setAddr("산골짜기0"+i);
			list.add(d);
		}
*/		String sql = "select * from jsp_member";
		try {
			ps = con.prepareStatement(sql);  //명령어를 전송하는 객체
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberDTO d = new MemberDTO();
				d.setId( rs.getString("id"));
				d.setPwd( rs.getString("pwd"));
				d.setName( rs.getString("name"));
				d.setAddr( rs.getString("addr"));
				list.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//추가하기 
	public int insertMem(MemberDTO dto) {
/*		System.out.println("데이터 베이스 저장");
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getPwd());
		System.out.println(dto.getAddr());
*/
		String sql = 
			"insert into jsp_member(id, pwd, name, addr) values(?,?,?,?)";
		int result = 0;
		try {
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getId());
				ps.setString(2, dto.getPwd());
				ps.setString(3, dto.getName());
				ps.setString(4, dto.getAddr());
				result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; //성공하면 1, 실패하면 0또는 -1 리턴시킴 
	}
	
	//유저 한명 선택하기 
	public MemberDTO memberInfo(String userId) {
		String sql="select * from jsp_member where id=?";
//		String sql="select * from jsp_member where id=' "+userId+" ' " ;
		MemberDTO dto = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);  //쿼리문에 직접 넣었으면 생략 가능
			rs = ps.executeQuery(); //하나에 대한 정보만 가져올 때, 굳이 while을 쓸 필요 없다!
			if(rs.next()) { //만약 데이터가 있다면~ 하나 있을땐. dto == null이면 데이터가 없다! 
				dto = new MemberDTO();
				dto.setId( rs.getString("id"));
				dto.setPwd( rs.getString("pwd"));
				dto.setName( rs.getString("name"));
				dto.setAddr( rs.getString("addr"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//수정하기 
	public int modify_save(MemberDTO dto) {
		String sql = "update jsp_member set addr=?, name=?, pwd=? where id=?";
		int result=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getAddr());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getPwd());
			ps.setString(4, dto.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
				e.printStackTrace();
		}
		return result; //성공하면 1, 실패하면 0또는 -1 반납 
	}
	
	//삭제하기 
	public int delete(String userId) {
		String sql = "delete from jsp_member where id= ' "+userId+" ' ";
		int result = 0;
		try {
			ps= con.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return result;
	}
	
	//로그인 검증 
	public int loginConfirm(String userId, String userPwd) {
		String sql = "select id,pwd from jsp_member where id=?";
		//중복확인 완료 1, 아이디없음 -1 비밀번호 없음 0
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getString("pwd").equals(userPwd)) {
					result = 1;
				} else {
					result = 0;
				}
			}else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
}







