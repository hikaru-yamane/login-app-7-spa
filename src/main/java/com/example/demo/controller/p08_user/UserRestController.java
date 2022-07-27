package com.example.demo.controller.p08_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.form.p08_user.UserForm;
import com.example.demo.service.p08_user.UserService;
import com.example.demo.validation.p08_user.ValidatorSequence;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService service;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void  register(@RequestBody @Validated({ValidatorSequence.class}) UserForm form, BindingResult result) {
		if (result.hasErrors()) {
			throw new RuntimeException("サーバ側のバリデーションでエラー発生");
		}
		String name = form.getName();
		String email = form.getEmail();
		String password = passwordEncoder.encode(form.getPassword());
		try {
			boolean success = service.registerUser(name, email, password);
			if (!success) {
				throw new RuntimeException();
			}
		} catch (RuntimeException e) {
			throw new RuntimeException("このメールアドレスはすでに使用されています。");
		}
	}

}
