package com.pictohotels;

import com.pictohotels.model.Address;
import com.pictohotels.model.Hotel;
import com.pictohotels.model.Review;
import com.pictohotels.repository.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Artemas on 02/08/2017.
 */
@Component
public class DbSeeder implements CommandLineRunner {

    private HotelRepository hotelRepository;

    public DbSeeder(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Hotel marriot = new Hotel(
                "Marriot",
                130,
                new Address("Paris", "France"),
                Arrays.asList(
                        new Review("John", 8, false),
                        new Review("Mary", 7, true)
                )
        );

        Hotel holidayInn = new Hotel(
                "Holiday Inn",
                60,
                new Address("London", "UK"),
                Arrays.asList(
                        new Review("Stephen", 4, false),
                        new Review("Paul", 7, true),
                        new Review("Sarah", 6, true)
                )
        );

        Hotel hiton = new Hotel(
                "Hilton",
                99,
                new Address("Birmingham", "UK"),
                Arrays.asList(
                        new Review("Shawn", 9, true)
                )
        );

        // Drop the existing collection
        this.hotelRepository.deleteAll();

        // Add all hotels to DB
        List<Hotel> hotels = Arrays.asList(marriot, holidayInn, hiton);
        this.hotelRepository.save(hotels);
    }
}
