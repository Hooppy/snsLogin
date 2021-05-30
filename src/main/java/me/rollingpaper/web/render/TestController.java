package me.rollingpaper.web.render;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/test1")
	public String test1(Model model) {
		return "test1";
	}
	
	@GetMapping("/api/member/userInfo")
	public String userInfo(Principal principal) {
		System.out.println("------------------------API1111------------------------");
		String name;
		try {
			name = principal.getName();
			System.out.println("name : " + name);
		}catch(NullPointerException e) {
			return "로그인해주세요.";
		}
		return name;
	}
	
}
