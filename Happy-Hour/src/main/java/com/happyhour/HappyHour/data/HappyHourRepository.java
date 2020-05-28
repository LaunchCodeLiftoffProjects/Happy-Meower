package com.happyhour.HappyHour.data;

import com.happyhour.HappyHour.models.HappyHour;
import org.springframework.data.repository.CrudRepository;

public interface HappyHourRepository extends CrudRepository<HappyHour, Integer> {

    HappyHour findByName(String name);
}
