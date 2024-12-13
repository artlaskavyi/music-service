package com.arty.musicservice.service;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.model.Album;
import com.arty.musicservice.model.Artist;
import com.arty.musicservice.record.AlbumDTO;
import com.arty.musicservice.repository.AlbumRepository;
import com.arty.musicservice.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public AlbumDTO saveAlbum(AlbumDTO albumDTO) {
        Artist artist = artistRepository.findById(albumDTO.artistId())
                .orElseThrow(() -> new EntityNotFoundException("Artist", albumDTO.artistId()));
        Album album = new Album();
        album.setTitle(albumDTO.title());
        album.setReleaseYear(albumDTO.releaseYear());
        album.setArtist(artist);

        albumRepository.save(album);
        return albumDTO;
    }

    public AlbumDTO findAlbumById(Integer id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Album", id));

        return new AlbumDTO(album.getAlbumId(), album.getTitle(), album.getReleaseYear(), album.getArtist().getArtistId());
    }

    public List<AlbumDTO> findAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        return albums.stream()
                .map(album -> new AlbumDTO(album.getAlbumId(), album.getTitle(), album.getReleaseYear(), album.getArtist().getArtistId()))
                .toList();
    }

    public void deleteAlbum(Integer id) {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Album", id);
        }
    }

    public List<AlbumDTO> findAllAlbumsByArtist(String artistName) {
        List<Album> albums = albumRepository.findByArtist_ArtistName(artistName);
        return albums.stream()
                .map(album -> new AlbumDTO(album.getAlbumId(), album.getTitle(), album.getReleaseYear(), album.getArtist().getArtistId()))
                .toList();
    }

    public List<AlbumDTO> findAlbumsByYear(Integer year) {
        List<Album> albums = albumRepository.findByReleaseYear(year);
        return albums.stream()
                .map(album -> new AlbumDTO(album.getAlbumId(), album.getTitle(), album.getReleaseYear(), album.getArtist().getArtistId()))
                .toList();
    }

    public List<AlbumDTO> findAlbumsByTitle(String title) {
        List<Album> albums = albumRepository.findByTitle(title);
        return albums.stream()
                .map(album -> new AlbumDTO(album.getAlbumId(), album.getTitle(), album.getReleaseYear(), album.getArtist().getArtistId()))
                .toList();
    }
}
