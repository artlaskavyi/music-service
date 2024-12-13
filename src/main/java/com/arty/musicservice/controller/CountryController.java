package com.arty.musicservice.controller;

import com.arty.musicservice.record.CountryDTO;
import com.arty.musicservice.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryDTO> getAllCountries() {
        return countryService.findAllCountries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Integer id) {
        return ResponseEntity.ok(countryService.findCountryById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) {
        return ResponseEntity.ok(countryService.saveCountry(countryDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCountry(@PathVariable Integer id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}