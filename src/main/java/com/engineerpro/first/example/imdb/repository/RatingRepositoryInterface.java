package com.engineerpro.first.example.imdb.repository;

import java.util.List;
import java.util.Map;

import com.engineerpro.first.example.imdb.model.Company;

public interface RatingRepositoryInterface {

    void saveUserRating(int userId, int companyId, int numberOfStar);

    void addCompany(Company company);

    boolean hasCompany(int companyId);

    List<Company> getCompanyById(List<Integer> CompanyIds);

    Map<Integer, Integer> getUserRated(int userId);
}
