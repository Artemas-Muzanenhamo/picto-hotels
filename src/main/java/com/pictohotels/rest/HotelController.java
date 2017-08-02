package com.pictohotels.rest;

import com.pictohotels.model.Hotel;
import com.pictohotels.model.QHotel;
import com.pictohotels.repository.HotelRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Artemas on 02/08/2017.
 */
@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/all")
    public List<Hotel> getAll(){
        List<Hotel> hotels = this.hotelRepository.findAll();

        return hotels;
    }

    @PutMapping
    public void insert(@RequestBody Hotel hotel){
        // insert = will only just insert a new hotel.
        this.hotelRepository.insert(hotel);
    }

    @PostMapping
    public void update(@RequestBody Hotel hotel){
        // save = works like an upsert. So if the hotel doesn't exist, Mongo will create it with a new Id.
        this.hotelRepository.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.hotelRepository.delete(id);
    }


    @GetMapping("/{id}")
    public Hotel getById(@PathVariable("id") String id){
        Hotel hotel = this.hotelRepository.findById(id);

        return hotel;
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getPricePerNight(@PathVariable("maxPrice") int maxPrice){
        List<Hotel> hotels = this.hotelRepository.findByPricePerNightLessThan(maxPrice);

        return hotels;
    }

    @GetMapping("/address/{city}")
    public List<Hotel> getByCity(@PathVariable("city") String city){
        List<Hotel> hotels = this.hotelRepository.findByCity(city);

        return hotels;
    }

    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable("country") String country){
        // Create a query class - QHotel
        QHotel qHotel = new QHotel("hotel");

        // using the query class, we can create our filters
        BooleanExpression filterByCountry = qHotel.address.country.eq(country);

        // we can pass the filters to the findAll() method.
        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByCountry);

        return hotels;
    }

    @GetMapping("/recommended")
    public List<Hotel> getRecommended(){

        final int maxPrice = 100;
        final int rating = 7;

        QHotel qHotel = new QHotel("hotel");

        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(rating);

        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterByRating));

        return hotels;
    }

}
