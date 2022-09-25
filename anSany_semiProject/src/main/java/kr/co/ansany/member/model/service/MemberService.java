package kr.co.ansany.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.ansany.member.model.dao.MemberDao;
import kr.co.ansany.member.model.vo.Member;
import kr.co.ansany.member.model.vo.MyPageOrderData;
import kr.co.ansany.member.model.vo.MyQnAData;
import kr.co.ansany.member.model.vo.MypageList;

public class MemberService {
	private MemberDao dao;

	public MemberService() {
		super();
		dao= new MemberDao();
	}
	//login
	public Member selectOneMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = dao.selectOneMember(conn,member);
		JDBCTemplate.close(conn);
		return m;
	}

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertMember(conn,m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//id를 통한 update
	public Member selectOneMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = dao.selectChkMember(conn, memberId);
		JDBCTemplate.close(conn);
		return m;
	}
	public int deleteMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteMember(conn,memberId);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public int updateMemberInfo(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateMemberInfo(conn, member);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public MypageList myPageList(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<MyPageOrderData>list = dao.myPageList(conn,memberId);
		ArrayList<MyQnAData>qnalist = dao.myQnAList(conn,memberId);
		
		MypageList mpl = new MypageList(list, qnalist);
		
		JDBCTemplate.close(conn);
		return mpl;
	}
	public MyQnAData deleteQnA(int qnaNo) {
		Connection conn = JDBCTemplate.getConnection();
		MyQnAData mqd = dao.selectOneQnA(conn,qnaNo);
		int result = dao.deleteQna(conn,qnaNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			
			mqd = null;
		}
		JDBCTemplate.close(conn);
		return mqd;
	}
	
	
}
