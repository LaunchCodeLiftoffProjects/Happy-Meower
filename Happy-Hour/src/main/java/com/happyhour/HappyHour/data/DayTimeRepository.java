package com.happyhour.HappyHour.data;

import com.happyhour.HappyHour.models.DayTime;
import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;
import java.util.List;


public interface DayTimeRepository extends CrudRepository<DayTime,Integer> {

    List<DayTime> findByDayOfWeek(DayOfWeek dayOfWeek);

}
