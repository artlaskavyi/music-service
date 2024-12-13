package com.arty.musicservice.repository;

import com.arty.musicservice.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    List<Playlist> findByUser_Username(String username);
    List<Playlist> findByTitle(String title);
}
