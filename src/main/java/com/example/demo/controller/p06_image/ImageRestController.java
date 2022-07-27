package com.example.demo.controller.p06_image;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.form.p06_image.ImageForm;

@RestController
@RequestMapping("/image")
public class ImageRestController {
	
	@PostMapping("/register")
	public ImageForm register(@RequestBody ImageForm form) throws IOException {
		return form;
	}

}
