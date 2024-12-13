package com.arty.musicservice.repository;

import com.arty.musicservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findByCountryName(String countryName);
}