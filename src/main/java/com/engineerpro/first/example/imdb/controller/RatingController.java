package com.engineerpro.first.example.imdb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.engineerpro.first.example.imdb.exception.InvalidInputException;
import com.engineerpro.first.example.imdb.model.Company;
import com.engineerpro.first.example.imdb.model.User;
import com.engineerpro.first.example.imdb.repository.RatingRepositoryInterface;

public class RatingController {
    private static final List<Integer> VALID_STAR_RATINGS = Arrays.asList(1, 2, 3, 4, 5);

    private RatingRepositoryInterface ratingRepositoryInterface;

    public RatingController(RatingRepositoryInterface ratingRepositoryInterface) {
        this.ratingRepositoryInterface = ratingRepositoryInterface;
    }

    public void rateCompany(User user, int companyId, int numberOfStar) throws Exception {
        if (!VALID_STAR_RATINGS.contains(numberOfStar)) {
            throw new InvalidInputException("Invalid number of star, a valid star must be in [1,2,3,4,5]");
        }
        if (!ratingRepositoryInterface.hasCompany(companyId)) {
            throw new InvalidInputException("Invalid movieId =" + companyId);
        }
        this.ratingRepositoryInterface.saveUserRating(user.getId(), companyId, numberOfStar);
    }

    public Map<Company, Integer> getUserRated(User user) throws Exception {
        Map<Integer, Integer> userRatedMovies = this.ratingRepositoryInterface.getUserRated(user.getId());
        List<Integer> movieIds = new ArrayList<>();
        for (int movieId : userRatedMovies.keySet()) {
            movieIds.add(movieId);
        }

        List<Company> userRatedMovieIds = ratingRepositoryInterface.getCompanyById(movieIds);

        Map<Company, Integer> results = new HashMap<>();
        for (int movieId : userRatedMovies.keySet()) {
            int rating = userRatedMovies.get(movieId);
            Company ratedmovie = userRatedMovieIds.stream().filter(movie -> movie.getId() == movieId).findFirst().get();
            results.put(ratedmovie, rating);
        }
        ;

        return results;
    }
}
