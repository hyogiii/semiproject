package kr.co.ansany.member.model.service;

import java.net.ConnectException;
import java.sql.Connection;

import common.JDBCTemplate;
import kr.co.ansany.member.model.dao.MemberDao;
import kr.co.ansany.member.model.vo.Member;

public class MemberService {
	private MemberDao dao;

	public MemberService() {
		super();
		dao= new MemberDao();
	}

	public Member selectOneMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = dao.selectOneMember(conn,member);
		JDBCTemplate.close(conn);
		return m;
	}
	
	
}
