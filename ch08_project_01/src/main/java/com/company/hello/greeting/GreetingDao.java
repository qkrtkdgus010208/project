package com.company.hello.greeting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class GreetingDao {

	private Map<String, GreetingVo> greetingDB = new HashMap<String, GreetingVo>();

	public GreetingVo insertMember(GreetingVo greetingVo) {
		System.out.println("[GreetingDao] insertMember()");

		System.out.println("m_id: " + greetingVo.getM_id());
		System.out.println("m_pw: " + greetingVo.getM_pw());
		System.out.println("m_lan: " + greetingVo.getM_lan());

		greetingDB.put(greetingVo.getM_id(), greetingVo);
		printAllMember(); 
		
		GreetingVo signUpedMember = greetingDB.get(greetingVo.getM_id());
	
		if(signUpedMember != null)
			return signUpedMember;
		else
			return null;
	}
	
	public GreetingVo selectMember(GreetingVo greetingVo) {
		System.out.println("[GreetingDao] selectMemeber()");
		
		GreetingVo signInedMember = greetingDB.get(greetingVo.getM_id());
		
		if(signInedMember != null && greetingVo.getM_pw().equals(signInedMember.getM_pw()))
			return signInedMember;
		else
			return null;
	}

	private void printAllMember() {
		System.out.println("[GreetingDao] printAllMember()");
		
		Set<String> keys = greetingDB.keySet();
		Iterator<String> iterator = keys.iterator();
		
		while (iterator.hasNext()) {
			String key = iterator.next();
			GreetingVo greetingVo = greetingDB.get(key);
			
			System.out.println("m_id: " + greetingVo.getM_id());
			System.out.println("m_pw: " + greetingVo.getM_pw());
			System.out.println("m_mail: " + greetingVo.getM_lan());
		}
	}
}
