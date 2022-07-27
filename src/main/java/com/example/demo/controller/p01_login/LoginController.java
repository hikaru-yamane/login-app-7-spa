package com.example.demo.controller.p01_login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceView;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public InternalResourceView view() {
		return new InternalResourceView("/index.html");
	}

}
