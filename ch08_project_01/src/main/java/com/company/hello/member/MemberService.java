package com.company.hello.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDao memberDao;

	public int signUpConfirm(MemberVO memberVo) {
		System.out.println("[MemberService] signUpConfirm()");

		System.out.println("m_id: " + memberVo.getM_id());
		System.out.println("m_pw: " + memberVo.getM_pw());
		System.out.println("m_mail: " + memberVo.getM_mail());
		System.out.println("m_phone: " + memberVo.getM_phone());

		memberDao.insertMember(memberVo);

		return 0;
	}

	public MemberVO signInConfirm(MemberVO memberVo) {
		System.out.println("[MemberService] signInConfirm");
		
		MemberVO signInedMember = memberDao.selectMember(memberVo);
		
		return signInedMember;
	}
}