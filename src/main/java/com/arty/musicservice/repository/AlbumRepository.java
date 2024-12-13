package com.arty.musicservice.repository;

import com.arty.musicservice.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByTitle(String title);
    List<Album> findByReleaseYear(Integer releaseYear);
    List<Album> findByArtist_ArtistName(String artistName);
}
