package com.arty.musicservice.repository;

import com.arty.musicservice.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findByTitle(String title);
    List<Song> findByAlbum_Title(String albumTitle);
}
