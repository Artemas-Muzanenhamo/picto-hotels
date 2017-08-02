package com.pictohotels.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artemas on 02/08/2017.
 */
@Document(collection = "Hotels")
public class Hotel {

    @Id
    private String id; // Because Mongo's id = 5349b4ddd2781d08c09890f4 so we cannot store it as *int*
    private String name;
    @Indexed(direction = IndexDirection.ASCENDING)
    private int pricePerNight;
    private Address address;
    private List<Review> reviews;

    protected Hotel(){
        this.reviews = new ArrayList<>();
    }


    public Hotel(String name, int pricePerNight, Address address, List<Review> reviews) {
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.address = address;
        this.reviews = reviews;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public Address getAddress() {
        return address;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
