package com.happyhour.HappyHour.data;

import com.happyhour.HappyHour.models.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Integer> {

    Owner findByUsername(String username);
}
