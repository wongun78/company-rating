package com.engineerpro.first.example.imdb;

import com.engineerpro.first.example.imdb.controller.RatingController;
import com.engineerpro.first.example.imdb.model.Company;
import com.engineerpro.first.example.imdb.model.User;
import com.engineerpro.first.example.imdb.repository.InMemoryRatingRepository;
import com.engineerpro.first.example.imdb.repository.RatingRepositoryInterface;

public class ImdbApp {
    public static void main(String[] args) throws Exception {

        User kien = new User(1, "kien", 21);

        User long2 = new User(2, "long", 21);

        Company Microsoft = new Company(101, "Microsoft");

        Company Apple = new Company(102, "Apple");

        Company SaudiAramco = new Company(103, "SaudiAramco");

        RatingRepositoryInterface inMemoryRatingRepository = new InMemoryRatingRepository();

        RatingController ratingController = new RatingController(inMemoryRatingRepository);

        inMemoryRatingRepository.addCompany(Microsoft);

        inMemoryRatingRepository.addCompany(Apple);

        inMemoryRatingRepository.addCompany(SaudiAramco);

        ratingController.rateCompany(kien, 101, 5);

        ratingController.rateCompany(kien, 102, 4);

        ratingController.rateCompany(long2, 102, 4);

        System.out.println(ratingController.getUserRated(kien));

        System.out.println("long rated: " + ratingController.getUserRated(long2));

    }
}
