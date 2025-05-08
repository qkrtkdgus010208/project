package com.company.hello.member;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class MemberDao {

	private Map<String, MemberVO> memberDB = new HashMap<String, MemberVO>();

	public void insertMember(MemberVO memberVo) {
		System.out.println("[MemberDao] insertMember()");

		System.out.println("m_id: " + memberVo.getM_id());
		System.out.println("m_pw: " + memberVo.getM_pw());
		System.out.println("m_mail: " + memberVo.getM_mail());
		System.out.println("m_phone: " + memberVo.getM_phone());

		memberDB.put(memberVo.getM_id(), memberVo);
		printAllMember(); 
	}
	
	public MemberVO selectMember(MemberVO memberVo) {
		System.out.println("[MemberDao] selectMemeber()");
		
		MemberVO signInedMember = memberDB.get(memberVo.getM_id());
		
		if(signInedMember != null && memberVo.getM_pw().equals(signInedMember.getM_pw()))
			return signInedMember;
		else
			return null;
	}

	private void printAllMember() {
		System.out.println("[MemberDao] printAllMember()");
		
		Set<String> keys = memberDB.keySet();
		Iterator<String> iterator = keys.iterator();
		
		while (iterator.hasNext()) {
			String key = iterator.next();
			MemberVO memberVo = memberDB.get(key);
			
			System.out.println("m_id: " + memberVo.getM_id());
			System.out.println("m_pw: " + memberVo.getM_pw());
			System.out.println("m_mail: " + memberVo.getM_mail());
			System.out.println("m_phone: " + memberVo.getM_phone());
		}
	}
}