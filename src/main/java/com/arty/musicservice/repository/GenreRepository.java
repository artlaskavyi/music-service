package com.arty.musicservice.repository;

import com.arty.musicservice.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByGenreName(String genreName);
}

