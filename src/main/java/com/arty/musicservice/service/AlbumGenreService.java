package com.arty.musicservice.service;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.model.Album;
import com.arty.musicservice.model.AlbumGenre;
import com.arty.musicservice.model.Genre;
import com.arty.musicservice.record.AlbumGenreDTO;
import com.arty.musicservice.repository.AlbumGenreRepository;
import com.arty.musicservice.repository.AlbumRepository;
import com.arty.musicservice.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumGenreService {
    private final AlbumGenreRepository albumGenreRepository;
    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;

    public AlbumGenreService(
            AlbumGenreRepository albumGenreRepository,
            AlbumRepository albumRepository,
            GenreRepository genreRepository
    ) {
        this.albumGenreRepository = albumGenreRepository;
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
    }

    public AlbumGenreDTO saveAlbumGenre(AlbumGenreDTO albumGenreDTO) {
        Album album = albumRepository.findById(albumGenreDTO.albumId())
                .orElseThrow(() -> new EntityNotFoundException("Album", albumGenreDTO.albumId()));

        Genre genre = genreRepository.findById(albumGenreDTO.genreId())
                .orElseThrow(() -> new EntityNotFoundException("Genre", albumGenreDTO.genreId()));

        AlbumGenre albumGenreEntity = new AlbumGenre();
        albumGenreEntity.setAlbum(album);
        albumGenreEntity.setGenre(genre);

        albumGenreRepository.save(albumGenreEntity);

        return albumGenreDTO;
    }

    public AlbumGenreDTO findAlbumGenreById(Integer id) {
        AlbumGenre albumGenreEntity = albumGenreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AlbumGenre", id));

        return new AlbumGenreDTO(
                albumGenreEntity.getAlbumGenreId(),
                albumGenreEntity.getAlbum().getAlbumId(),
                albumGenreEntity.getGenre().getGenreId()
        );
    }

    public List<AlbumGenreDTO> findAllAlbumGenres() {
        List<AlbumGenre> albumGenres = albumGenreRepository.findAll();
        return albumGenres.stream()
                .map(albumGenre -> new AlbumGenreDTO(
                        albumGenre.getAlbumGenreId(),
                        albumGenre.getAlbum().getAlbumId(),
                        albumGenre.getGenre().getGenreId()
                ))
                .toList();
    }

    public void deleteAlbumGenre(Integer id) {
        if (albumGenreRepository.existsById(id)) {
            albumGenreRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("AlbumGenre", id);
        }
    }
}