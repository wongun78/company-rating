package com.engineerpro.first.example.imdb.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.engineerpro.first.example.imdb.model.Company;

public class InMemoryRatingRepository implements RatingRepositoryInterface {

    private static final Map<Integer, Company> companyMap = new HashMap<>();
    private static final Map<Integer, Map<Integer, Integer>> userRatingMap = new HashMap<>();

    @Override
    public void saveUserRating(int userId, int companyId, int numberOfStar) {
        if (!userRatingMap.containsKey(userId)) {
            userRatingMap.put(userId, new HashMap<>());
        }
        userRatingMap.get(userId).put(companyId, numberOfStar);
    }

    @Override
    public void addCompany(Company company) {
        companyMap.put(company.getId(), company);
    }

    @Override
    public boolean hasCompany(int companyId) {
        return companyMap.containsKey(companyId);
    }

    @Override
    public List<Company> getCompanyById(List<Integer> companyIds) {
        List<Company> results = new ArrayList<>();
        for (int companyId : companyIds) {
            if (!hasCompany(companyId)) {
                throw new RuntimeException("Cannot find movie with id =" + companyId);
            }
            Company movie = companyMap.get(companyId);
            results.add(movie);
        }
        return results;
    }

    @Override
    public Map<Integer, Integer> getUserRated(int userId) {
        if (!userRatingMap.containsKey(userId)) {
            return new HashMap<>();
        }
        return userRatingMap.get(userId);
    }

}
