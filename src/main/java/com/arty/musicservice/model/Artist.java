package com.arty.musicservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Integer artistId;

    @Column(name = "artist_name", nullable = false)
    private String artistName;

    @Column(name = "year_of_creation")
    private Integer yearOfCreation;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Integer getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(Integer yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
