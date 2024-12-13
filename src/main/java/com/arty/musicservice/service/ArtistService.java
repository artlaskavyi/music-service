package com.arty.musicservice.service;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.model.Artist;
import com.arty.musicservice.record.ArtistDTO;
import com.arty.musicservice.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public ArtistDTO saveArtist(ArtistDTO artistDTO) {
        Artist artist = new Artist();
        artist.setArtistName(artistDTO.artistName());
        artist.setYearOfCreation(artistDTO.yearOfCreation());

        artistRepository.save(artist);
        return artistDTO;
    }

    public ArtistDTO findArtistById(Integer id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artist", id));

        return new ArtistDTO(artist.getArtistId(), artist.getArtistName(), artist.getYearOfCreation(), artist.getCountry().getCountryId());
    }

    public List<ArtistDTO> findAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        return artists.stream()
                .map(artist -> new ArtistDTO(artist.getArtistId(), artist.getArtistName(), artist.getYearOfCreation(), artist.getCountry().getCountryId()))
                .toList();
    }

    public void deleteArtist(Integer id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Artist", id);
        }
    }

    public List<ArtistDTO> findArtistByArtistName(String artistName) {
        List<Artist> artists = artistRepository.findByArtistName(artistName);
        return artists.stream()
                .map(artist -> new ArtistDTO(artist.getArtistId(), artist.getArtistName(), artist.getYearOfCreation(), artist.getCountry().getCountryId()))
                .toList();
    }

    public List<ArtistDTO> findArtistByYearOfCreation(Integer year) {
        List<Artist> artists = artistRepository.findByYearOfCreation(year);
        return artists.stream()
                .map(artist -> new ArtistDTO(artist.getArtistId(), artist.getArtistName(), artist.getYearOfCreation(), artist.getCountry().getCountryId()))
                .toList();
    }

    public List<ArtistDTO> findArtistByCountryName(String countryName) {
        List<Artist> artists = artistRepository.findByCountry_CountryName(countryName);
        return artists.stream()
                .map(artist -> new ArtistDTO(artist.getArtistId(), artist.getArtistName(), artist.getYearOfCreation(), artist.getCountry().getCountryId()))
                .toList();
    }
}