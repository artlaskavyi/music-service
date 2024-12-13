package com.arty.musicservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PlaylistSong")
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_song_id")
    private Integer playlistSongId;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    public Integer getPlaylistSongId() {
        return playlistSongId;
    }

    public void setPlaylistSongId(Integer playlistSongId) {
        this.playlistSongId = playlistSongId;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
