package com.company.hello.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

	@Autowired
	GreetingDao greetingDao;
	
	public GreetingVo signUpConfirm(GreetingVo greetingVo) {
		System.out.println("[GreetingService] signUpConfirm()");

		System.out.println("m_id: " + greetingVo.getM_id());
		System.out.println("m_pw: " + greetingVo.getM_pw());
		System.out.println("m_mail: " + greetingVo.getM_lan());

		GreetingVo signUpedMember = greetingDao.insertMember(greetingVo);
		
		return signUpedMember;
	}

	public GreetingVo signInConfirm(GreetingVo greetingVo) {
		System.out.println("[GreetingService] signInConfirm");
		
		GreetingVo signInedMember = greetingDao.selectMember(greetingVo);
		
		return signInedMember;
	}
}
