package com.arty.musicservice.repository;

import com.arty.musicservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByUser_Username(String username);
    List<Rating> findBySong_Title(String songTitle);
    List<Rating> findByRatingValue(Integer ratingValue);
}
