package com.arty.musicservice.repository;

import com.arty.musicservice.model.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Integer> {
}
