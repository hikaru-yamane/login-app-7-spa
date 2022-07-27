package com.example.demo.form.p08_user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.demo.validation.p08_user.Groups.Group1;
import com.example.demo.validation.p08_user.Groups.Group2;
import com.example.demo.validation.p08_user.Groups.Group3;
import com.example.demo.validation.p08_user.Groups.Group4;
import com.example.demo.validation.p08_user.Groups.Group5;
import com.example.demo.validation.p08_user.NameEmailLengthAnnotation;

import lombok.Data;

@Data
@NameEmailLengthAnnotation(min=2, max=20, groups=Group5.class)
public class UserForm {
	
	@NotEmpty(groups=Group1.class)
	private String name;
	
	@NotEmpty(groups=Group2.class)
	@Email(groups=Group4.class)
	private String email;
	
	@NotEmpty(groups=Group3.class)
	@Length(min=8, groups=Group4.class)
	private String password;
	
}
