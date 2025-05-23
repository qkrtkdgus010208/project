package com.company.hello.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping("/member/signUp")
	public String signUp() {
		return "sign_up";
	}

	@RequestMapping("/member/signIn")
	public String signIn() {
		return "sign_in";
	}

	@RequestMapping("/member/signUpConfirm")
	public String signUpConfirm(MemberVO memberVo) {
		System.out.println("[MemberController] signUpConfirm()");

		System.out.println("m_id: " + memberVo.getM_id());
		System.out.println("m_pw: " + memberVo.getM_pw());
		System.out.println("m_mail: " + memberVo.getM_mail());
		System.out.println("m_phone: " + memberVo.getM_phone());

		memberService.signUpConfirm(memberVo);

		return "sign_up_ok";
	}

	@RequestMapping("/member/signInConfirm")
	public String signInConfirm(MemberVO memberVo) {
		System.out.println("[MemberController] signUpConfirm()");
		
		MemberVO signInedMember = memberService.signInConfirm(memberVo);
		
		if(signInedMember != null)
			return "sign_in_ok";
		else
			return "sign_in_ng";
	}
}
