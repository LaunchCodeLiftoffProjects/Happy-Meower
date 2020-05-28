package com.happyhour.HappyHour.data;

import com.happyhour.HappyHour.models.HappyHour;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HappyHourRepository extends CrudRepository<HappyHour, Integer> {

    public List<HappyHour> findTop5ByOrderByIdDesc();

    HappyHour findByName(String name);
}
