package com.example.demo.validation.p08_user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameEmailLengthValidator.class)
public @interface NameEmailLengthAnnotation {

	String message() default "{com.example.demo.validation.p09_user.NameEmailLengthValidator.message}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	int min() default 0;
	
	int max() default Integer.MAX_VALUE;

}
