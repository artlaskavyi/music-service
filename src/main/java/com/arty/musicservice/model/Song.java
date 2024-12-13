package com.arty.musicservice.model;

import jakarta.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "Song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Integer songId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "song_length", nullable = false)
    private Time songLength;

    @Column(name = "lyrics")
    private String lyrics;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Time getSongLength() {
        return songLength;
    }

    public void setSongLength(Time songLength) {
        this.songLength = songLength;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
