package com.example.demo.validation.p08_user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.form.p08_user.UserForm;

public class NameEmailLengthValidator implements ConstraintValidator<NameEmailLengthAnnotation, UserForm> {
	
	private int min;
	private int max;
	
	@Override
	public void initialize(NameEmailLengthAnnotation annotation) {
		min = annotation.min();
		max = annotation.max();
	}
	
	@Override
	public boolean isValid(UserForm form, ConstraintValidatorContext context) {
		int totalLength = form.getName().length() + form.getEmail().length();
		return totalLength >= min && totalLength <= max;
	}

}
