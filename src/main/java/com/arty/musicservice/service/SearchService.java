package com.arty.musicservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class SearchService {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final SongService songService;
    private final PlaylistService playlistService;
    private final UserService userService;

    @Autowired
    public SearchService(AlbumService albumService, ArtistService artistService,
                         SongService songService, PlaylistService playlistService, UserService userService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.songService = songService;
        this.playlistService = playlistService;
        this.userService = userService;
    }

    public Map<String, Object> search(String query) {
        Map<String, Object> results = new LinkedHashMap<>();

        // Search artists
        results.put("artists", artistService.findArtistByArtistName(query));

        // Search albums
        results.put("albums", albumService.findAlbumsByTitle(query));

        // Search songs
        results.put("songs", songService.findSongsByTitle(query));

        // Search playlists
        results.put("playlists", playlistService.findPlaylistsByUserName(query));

        // Search users
        try {
            results.put("users", userService.findUserByUsername(query));
        } catch (Exception e) {
            results.put("users", Collections.emptyList());
        }
        return results;
    }
}