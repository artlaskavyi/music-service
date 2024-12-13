package com.arty.musicservice.model;


import jakarta.persistence.*;

@Entity
@Table(name = "AlbumGenre")
public class AlbumGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_genre_id")
    private Integer albumGenreId;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    public Integer getAlbumGenreId() {
        return albumGenreId;
    }

    public void setAlbumGenreId(Integer albumGenreId) {
        this.albumGenreId = albumGenreId;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
