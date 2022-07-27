package com.example.demo.repository.p04_time;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.p04_time.Time;

public interface TimeRepository extends CrudRepository<Time, Integer> {
	
//	@Query("SELECT tp.name AS work_type, tm.date_time AS date_time FROM work_time AS tm JOIN work_type AS tp ON tm.work_type_id = tp.id WHERE tm.user_id = :user_id ORDER BY tm.date_time")
//	List<Time> getTimeListOld(@Param("user_id") Integer userId);
	
	@Query("SELECT tp.name AS work_type, tm.date_time AS date_time FROM (SELECT work_type_id, date_time FROM work_time WHERE user_id = :user_id) AS tm JOIN work_type AS tp ON tm.work_type_id = tp.id ORDER BY tm.date_time")
	List<Time> getTimeList(@Param("user_id") Integer userId);
	
	@Modifying
	@Query("INSERT INTO work_time (user_id, work_type_id, date_time) values (:user_id, (SELECT id FROM work_type WHERE name = :name), :date_time)")
	boolean registerTime(@Param("user_id") Integer userId, @Param("name") String workType, @Param("date_time") String dateTime);

}
