package com.happyhour.HappyHour.data;

import com.happyhour.HappyHour.models.HappyHour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HappyHourRepository extends CrudRepository<HappyHour, Integer> {

    HappyHour findByName(String name);

    HappyHour findByDayOfWeek(String dayOfWeek);
}
