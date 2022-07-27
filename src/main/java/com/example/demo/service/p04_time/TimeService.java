package com.example.demo.service.p04_time;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.p04_time.Time;
import com.example.demo.repository.p04_time.TimeRepository;

@Service
public class TimeService {

	@Autowired
	private TimeRepository repository;

	public List<Time> getTimeList(Integer userId) {
		return repository.getTimeList(userId);
	}
	
	public boolean registerTime(Integer userId, String workType, String dateTime) {
		return repository.registerTime(userId, workType, dateTime);
	}

}
