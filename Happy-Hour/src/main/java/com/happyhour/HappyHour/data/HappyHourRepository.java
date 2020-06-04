package com.happyhour.HappyHour.data;

import com.happyhour.HappyHour.models.HappyHour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HappyHourRepository extends CrudRepository<HappyHour, Integer> {

    public List<HappyHour> findTop5ByOrderByIdDesc();

    HappyHour findByName(String name);
}
