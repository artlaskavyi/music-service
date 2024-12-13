package com.arty.musicservice.service;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.model.Playlist;
import com.arty.musicservice.model.User;
import com.arty.musicservice.record.PlaylistDTO;
import com.arty.musicservice.repository.PlaylistRepository;
import com.arty.musicservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }

    public PlaylistDTO savePlaylist(PlaylistDTO playlistDTO) {
        User user = userRepository.findById(playlistDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("User", playlistDTO.userId()));

        Playlist playlist = new Playlist();
        playlist.setTitle(playlistDTO.title());
        playlist.setUser(user);

        playlistRepository.save(playlist);
        return playlistDTO;
    }

    public PlaylistDTO updatePlaylist(Integer id, PlaylistDTO playlistDTO) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Playlist", id));

        User user = userRepository.findById(playlistDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("User", playlistDTO.userId()));

        playlist.setTitle(playlistDTO.title());
        playlist.setUser(user);
        playlist.setDescription(playlistDTO.description());

        playlistRepository.save(playlist);
        return new PlaylistDTO(playlist.getPlaylistId(), playlist.getTitle(), playlist.getDescription(), playlist.getUser().getUserId());
    }

    public PlaylistDTO findPlaylistById(Integer id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Playlist", id));
        return new PlaylistDTO(playlist.getPlaylistId(), playlist.getTitle(), playlist.getDescription(), playlist.getUser().getUserId());
    }

    public List<PlaylistDTO> findAllPlaylists() {
        List<Playlist> playlists = playlistRepository.findAll();
        return playlists.stream()
                .map(playlist -> new PlaylistDTO(playlist.getPlaylistId(), playlist.getTitle(), playlist.getDescription(), playlist.getUser().getUserId()))
                .toList();
    }

    public List<PlaylistDTO> findPlaylistsByUserName(String username) {
        List<Playlist> playlists = playlistRepository.findByUser_Username(username);
        return playlists.stream()
                .map(playlist -> new PlaylistDTO(playlist.getPlaylistId(), playlist.getTitle(), playlist.getDescription(), playlist.getUser().getUserId()))
                .toList();
    }

    public List<PlaylistDTO> findPlaylistsByTitle(String title) {
        List<Playlist> playlists = playlistRepository.findByTitle(title);
        return playlists.stream()
                .map(playlist -> new PlaylistDTO(playlist.getPlaylistId(), playlist.getTitle(), playlist.getDescription(), playlist.getUser().getUserId()))
                .toList();
    }

    public void deletePlaylist(Integer id) {
        if (playlistRepository.existsById(id)) {
            playlistRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Playlist", id);
        }
    }
}