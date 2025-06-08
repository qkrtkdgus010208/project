package com.office.library.user.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/member")
public class UserMemberController {

	@Autowired
	UserMemberService userMemberService;

	// 회원가입
	@GetMapping("/createAccountForm")
	public String createAccountForm() {
		System.out.println("[UserMemberController] createAccountForm()");

		String nextPage = "user/member/create_account_form";

		return nextPage;
	}

	@PostMapping("/createAccountConfirm")
	public String createAccountConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberController] createAccountConfirm()");

		String nextPage = "user/member/create_account_ok";

		int result = userMemberService.createAccountConfirm(userMemberVo);

		if (result <= 0)
			nextPage = "user/member/create_account_ng";

		return nextPage;
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		System.out.println("[UserMemberController] loginForm()");

		String nextPage = "user/member/login_form";

		return nextPage;
	}

	@PostMapping("/loginConfirm")
	public String loginConfirm(UserMemberVo userMemberVo, HttpSession session) {
		System.out.println("[UserMemberController] loginConfirm()");

		String nextPage = "user/member/login_ok";

		UserMemberVo loginedUserMemberVo = userMemberService.loginConfirm(userMemberVo);

		if (loginedUserMemberVo == null) {
			nextPage = "user/member/login_ng";
		} else {
			session.setAttribute("loginedUserMemberVo", loginedUserMemberVo);
			session.setMaxInactiveInterval(60 * 30);
		}

		return nextPage;
	}

	@GetMapping("/modifyAccountForm")
	public String modifyAccountForm(HttpSession session) {
		System.out.println("[UserMemberController] modifyAccountForm()");

		String nextPage = "user/member/modify_account_form";

		return nextPage;
	}

	@PostMapping("/modifyAccountConfirm")
	public String modifyAccountConfirm(UserMemberVo userMemberVo, HttpSession session) {
		System.out.println("[UserMemberController] modifyAccountConfirm()");

		String nextPage = "user/member/modify_account_ok";

		int result = userMemberService.modifyAccountConfirm(userMemberVo);

		if (result > 0) {
			UserMemberVo loginedUserMemberVo = 
					userMemberService.getLoginedUserMemberVo(userMemberVo.getU_m_no());

			session.setAttribute("loginedUserMemberVo", loginedUserMemberVo);
			session.setMaxInactiveInterval(60 * 30);
		} else {
			nextPage = "user/member/modify_account_ng";
		}

		return nextPage;
	}
	
	@GetMapping("/deleteAccountForm")
	public String deleteAccountForm() {
	    System.out.println("[UserMemberController] deleteAccountForm()");
	    
	    String nextPage = "user/member/delete_account_form";
	    
	    return nextPage;
	}

	@PostMapping("/deleteAccountConfirm")
	public String deleteAccountConfirm(UserMemberVo userMemberVo, HttpSession session) {
	    System.out.println("[UserMemberController] deleteAccountConfirm()");

	    String nextPage = "user/member/delete_account_ok";

	    // 세션에서 로그인된 회원 정보 가져오기
	    UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");

	    // 로그인 정보가 없거나 비밀번호 불일치 시
	    if (loginedUserMemberVo == null || 
	        !userMemberService.isPasswordMatch(userMemberVo.getU_m_pw(), loginedUserMemberVo.getU_m_pw())) {
	        nextPage = "user/member/delete_account_ng";
	        return nextPage;
	    }

	    // 비밀번호 일치 시 삭제
	    int result = userMemberService.deleteAccountConfirm(loginedUserMemberVo); // 삭제는 세션 정보 기준으로

	    if (result > 0) {
	        session.invalidate(); // 로그아웃 처리
	    } else {
	        nextPage = "user/member/delete_account_ng";
	    }

	    return nextPage;
	}


	@GetMapping("/logoutConfirm")
	public String logoutConfirm(HttpSession session) {
		System.out.println("[UserMemberController] logoutConfirm()");

		String nextPage = "redirect:/";
		session.invalidate();

		return nextPage;
	}

	@GetMapping("/findPasswordForm")
	public String findPasswordForm() {
		System.out.println("[UserMemberController] findPasswordForm()");

		String nextPage = "user/member/find_password_form";

		return nextPage;
	}

	@PostMapping("/findPasswordConfirm")
	public String findPasswordConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberController] findPasswordConfirm()");
		
		String nextPage = "user/member/find_password_ok";
		
		int result = userMemberService.findPasswordConfirm(userMemberVo);
		
		if (result <= 0)
			nextPage = "user/member/find_password_ng";
		
		return nextPage;
	}
}
