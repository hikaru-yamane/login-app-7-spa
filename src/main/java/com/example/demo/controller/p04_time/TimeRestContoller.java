package com.example.demo.controller.p04_time;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.p04_time.Time;
import com.example.demo.form.p04_time.TimeForm;
import com.example.demo.service.p04_time.TimeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/time")
@Slf4j
public class TimeRestContoller {
	
	@Autowired
	private TimeService service;
	
	@PostMapping("/get")
	public List<Time> get(@RequestBody TimeForm form) {
		Integer userId = form.getUserId();
		List<Time> timeList = service.getTimeList(userId);
		return timeList;
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void register(@RequestBody TimeForm form) {
		Integer userId = form.getUserId();
		String workType = form.getWorkType();
		String dateTime = form.getDateTime();
		boolean success = service.registerTime(userId, workType, dateTime);
		if (!success) {
			throw new RuntimeException("勤怠を登録できませんでした。");
		}
		log.info(workType + " " + dateTime);
	}

}
