package com.arty.musicservice.repository;

import com.arty.musicservice.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    List<Artist> findByArtistName(String artistName);
    List<Artist> findByYearOfCreation(Integer year);
    List<Artist> findByCountry_CountryName(String countryName);
}

