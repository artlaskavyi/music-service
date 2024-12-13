package com.arty.musicservice.service;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.exception.FieldNotUniqueException;
import com.arty.musicservice.model.Country;
import com.arty.musicservice.record.CountryDTO;
import com.arty.musicservice.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryDTO saveCountry(CountryDTO countryDTO) {
        if (countryRepository.findByCountryName(countryDTO.countryName()) != null) {
            throw new FieldNotUniqueException("Country Name", countryDTO.countryName());
        }
        Country country = new Country();
        country.setCountryName(countryDTO.countryName());

        countryRepository.save(country);
        return countryDTO;
    }

    public CountryDTO findCountryById(Integer id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country", id));

        return new CountryDTO(country.getCountryId(), country.getCountryName());
    }

    public List<CountryDTO> findAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(country -> new CountryDTO(country.getCountryId(), country.getCountryName()))
                .toList();
    }

    public void deleteCountry(Integer id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Country", id);
        }
    }

    public CountryDTO findByCountryName(String countryName) {
        Country country = countryRepository.findByCountryName(countryName);
        if (country == null) {
            throw new EntityNotFoundException("Country with name", countryName);
        }
        return new CountryDTO(country.getCountryId(), country.getCountryName());
    }
}