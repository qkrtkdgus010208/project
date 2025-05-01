package com.company.hello.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {
	
	@RequestMapping("/greet")
	public String greet() {
		return "greeting";
	}
}
