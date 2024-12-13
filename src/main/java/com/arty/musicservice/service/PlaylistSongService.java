package com.arty.musicservice.service;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.model.Playlist;
import com.arty.musicservice.model.PlaylistSong;
import com.arty.musicservice.model.Song;
import com.arty.musicservice.record.PlaylistSongDTO;
import com.arty.musicservice.repository.PlaylistRepository;
import com.arty.musicservice.repository.PlaylistSongRepository;
import com.arty.musicservice.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistSongService {

    private final PlaylistSongRepository playlistSongRepository;
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;

    public PlaylistSongService(PlaylistSongRepository playlistSongRepository, PlaylistRepository playlistRepository, SongRepository songRepository) {
        this.playlistSongRepository = playlistSongRepository;
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    public PlaylistSongDTO savePlaylistSong(PlaylistSongDTO playlistSongDTO) {
        Playlist playlist = playlistRepository.findById(playlistSongDTO.playlistId())
                .orElseThrow(() -> new EntityNotFoundException("Playlist", playlistSongDTO.playlistId()));

        Song song = songRepository.findById(playlistSongDTO.songId())
                .orElseThrow(() -> new EntityNotFoundException("Song", playlistSongDTO.songId()));

        PlaylistSong playlistSong = new PlaylistSong();
        playlistSong.setPlaylist(playlist);
        playlistSong.setSong(song);

        playlistSongRepository.save(playlistSong);
        return new PlaylistSongDTO(playlistSong.getPlaylistSongId(), playlist.getPlaylistId(), song.getSongId());
    }

    public void deletePlaylistSong(Integer id) {
        if (playlistSongRepository.existsById(id)) {
            playlistSongRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("PlaylistSong", id);
        }
    }

    public List<PlaylistSongDTO> findAllPlaylistSongs() {
        List<PlaylistSong> playlistSongs = playlistSongRepository.findAll();
        return playlistSongs.stream()
                .map(ps -> new PlaylistSongDTO(ps.getPlaylistSongId(), ps.getPlaylist().getPlaylistId(), ps.getSong().getSongId()))
                .toList();
    }

    public PlaylistSongDTO findPlaylistSongById(Integer id) {
        PlaylistSong playlistSong = playlistSongRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PlaylistSong", id));

        return new PlaylistSongDTO(playlistSong.getPlaylistSongId(), playlistSong.getPlaylist().getPlaylistId(), playlistSong.getSong().getSongId());
    }
}