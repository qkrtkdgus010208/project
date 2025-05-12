package com.company.hello.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.hello.member.MemberVO;

@Controller
public class GreetingController {
	
	@Autowired
	GreetingService greetingService;
	
	@RequestMapping("/greet")
	public String greet() {
		return "greeting";
	}
	
	@RequestMapping("/signUp")
	public String signUp() {
		return "greeting_sign_up";
	}

	@RequestMapping("/signIn")
	public String signIn() {
		return "greeting_sign_in";
	}
	
	@RequestMapping("/signUpConfirm")
	public String signUpConfirm(GreetingVo greetingVo) {
		System.out.println("[GreetingController] signUpConfirm()");

		System.out.println("m_id: " + greetingVo.getM_id());
		System.out.println("m_pw: " + greetingVo.getM_pw());
		System.out.println("m_mail: " + greetingVo.getM_lan());

		GreetingVo signUpedMember = greetingService.signUpConfirm(greetingVo);
	
		if(signUpedMember != null && signUpedMember.getM_lan().equals("en"))
			return "greeting_sign_up_en_ok";
		else
			return "greeting_sign_up_kor_ok"; 
	}

	@RequestMapping("/signInConfirm")
	public String signInConfirm(GreetingVo greetingVo) {
		System.out.println("[GreetingController] signUpConfirm()");
		
		GreetingVo signInedMember = greetingService.signInConfirm(greetingVo);
		
		if(signInedMember != null)
			if(signInedMember.getM_lan().equals("en"))
				return "greeting_sign_in_en_ok";
			else
				return "greeting_sign_in_kor_ok";	
		else
			return "greeting_sign_in_ng";
	}
}
