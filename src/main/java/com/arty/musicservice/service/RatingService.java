package com.arty.musicservice.service;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.model.Rating;
import com.arty.musicservice.model.User;
import com.arty.musicservice.model.Song;
import com.arty.musicservice.record.RatingDTO;
import com.arty.musicservice.repository.PlaylistRepository;
import com.arty.musicservice.repository.RatingRepository;
import com.arty.musicservice.repository.UserRepository;
import com.arty.musicservice.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final PlaylistRepository playlistRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, SongRepository songRepository, PlaylistRepository playlistRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
    }

    public RatingDTO saveRating(RatingDTO ratingDTO) {
        User user = userRepository.findById(ratingDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("User", ratingDTO.userId()));
        Song song = songRepository.findById(ratingDTO.songId())
                .orElseThrow(() -> new EntityNotFoundException("Song", ratingDTO.songId()));

        Rating rating = new Rating();
        rating.setRatingValue(ratingDTO.ratingValue());
        rating.setReviewText(ratingDTO.reviewText());
        rating.setUser(user);
        rating.setSong(song);

        ratingRepository.save(rating);
        return ratingDTO;
    }

    public RatingDTO updateRating(Integer id, RatingDTO ratingDTO) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating", id));

        User user = userRepository.findById(ratingDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("User", ratingDTO.userId()));
        Song song = songRepository.findById(ratingDTO.songId())
                .orElseThrow(() -> new EntityNotFoundException("Song", ratingDTO.songId()));

        rating.setRatingValue(ratingDTO.ratingValue());
        rating.setReviewText(ratingDTO.reviewText());
        rating.setUser(user);
        rating.setSong(song);

        ratingRepository.save(rating);
        return ratingDTO;
    }

    public RatingDTO findRatingById(Integer id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating", id));
        return new RatingDTO(rating.getRatingId(), rating.getRatingValue(), rating.getReviewText(),
                rating.getUser().getUserId(), rating.getSong().getSongId());
    }

    public List<RatingDTO> findAllRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings.stream()
                .map(rating -> new RatingDTO(rating.getRatingId(), rating.getRatingValue(), rating.getReviewText(),
                        rating.getUser().getUserId(), rating.getSong().getSongId()))
                .toList();
    }

    public List<RatingDTO> findRatingsByUserId(String username) {
        List<Rating> ratings = ratingRepository.findByUser_Username(username);
        return ratings.stream()
                .map(rating -> new RatingDTO(rating.getRatingId(), rating.getRatingValue(), rating.getReviewText(),
                        rating.getUser().getUserId(), rating.getSong().getSongId()))
                .toList();
    }

    public List<RatingDTO> findRatingsBySongId(String title) {
        List<Rating> ratings = ratingRepository.findBySong_Title(title);
        return ratings.stream()
                .map(rating -> new RatingDTO(rating.getRatingId(), rating.getRatingValue(), rating.getReviewText(),
                        rating.getUser().getUserId(), rating.getSong().getSongId()))
                .toList();
    }

    public List<RatingDTO> findRatingsByRatingValue(Integer ratingValue) {
        List<Rating> ratings = ratingRepository.findByRatingValue(ratingValue);
        return ratings.stream()
                .map(rating -> new RatingDTO(rating.getRatingId(), rating.getRatingValue(), rating.getReviewText(),
                        rating.getUser().getUserId(), rating.getSong().getSongId()))
                .toList();
    }

    public void deleteRating(Integer id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Rating", id);
        }
    }
}