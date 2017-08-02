package com.pictohotels.repository;

import com.pictohotels.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Artemas on 02/08/2017.
 */
@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>, QueryDslPredicateExecutor<Hotel>{

    Hotel findById(String id);

    List<Hotel> findByPricePerNightLessThan(int maxPrice);

    @Query(value = "{address.city: ?0}")
    List<Hotel> findByCity(String city);

}
