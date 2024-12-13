package com.arty.musicservice.service;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.model.Song;
import com.arty.musicservice.model.Album;
import com.arty.musicservice.record.SongDTO;
import com.arty.musicservice.repository.SongRepository;
import com.arty.musicservice.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongService(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    public SongDTO saveSong(SongDTO songDTO) {
        Album album = albumRepository.findById(songDTO.albumId())
                .orElseThrow(() -> new EntityNotFoundException("Album", songDTO.albumId()));

        Song song = new Song();
        song.setTitle(songDTO.title());
        song.setSongLength(songDTO.songLength());
        song.setLyrics(songDTO.lyrics());
        song.setAlbum(album);

        songRepository.save(song);
        return songDTO;
    }

    public SongDTO updateSong(Integer id, SongDTO songDTO) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Song", id));

        Album album = albumRepository.findById(songDTO.albumId())
                .orElseThrow(() -> new EntityNotFoundException("Album", songDTO.albumId()));

        song.setTitle(songDTO.title());
        song.setSongLength(songDTO.songLength());
        song.setLyrics(songDTO.lyrics());
        song.setAlbum(album);

        songRepository.save(song);
        return songDTO;
    }

    public SongDTO findSongById(Integer id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Song", id));
        return new SongDTO(song.getSongId(), song.getTitle(), song.getSongLength(), song.getLyrics(), song.getAlbum().getAlbumId());
    }

    public List<SongDTO> findAllSongs() {
        List<Song> songs = songRepository.findAll();
        return songs.stream()
                .map(song -> new SongDTO(song.getSongId(), song.getTitle(), song.getSongLength(), song.getLyrics(), song.getAlbum().getAlbumId()))
                .toList();
    }

    public List<SongDTO> findSongsByTitle(String title) {
        List<Song> songs = songRepository.findByTitle(title);
        return songs.stream()
                .map(song -> new SongDTO(song.getSongId(), song.getTitle(), song.getSongLength(), song.getLyrics(), song.getAlbum().getAlbumId()))
                .toList();
    }

    public List<SongDTO> findSongsByAlbumTitle(String albumTitle) {
        List<Song> songs = songRepository.findByAlbum_Title(albumTitle);
        return songs.stream()
                .map(song -> new SongDTO(song.getSongId(), song.getTitle(), song.getSongLength(), song.getLyrics(), song.getAlbum().getAlbumId()))
                .toList();
    }

    public void deleteSong(Integer id) {
        if (songRepository.existsById(id)) {
            songRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Song", id);
        }
    }
}