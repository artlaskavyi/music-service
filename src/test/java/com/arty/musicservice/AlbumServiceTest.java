package com.arty.musicservice;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.model.Album;
import com.arty.musicservice.model.Artist;
import com.arty.musicservice.record.AlbumDTO;
import com.arty.musicservice.repository.AlbumRepository;
import com.arty.musicservice.repository.ArtistRepository;
import com.arty.musicservice.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@Profile("!prod")
@ExtendWith(MockitoExtension.class)
public class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private AlbumService albumService;

    private Artist artist;
    private Album album;
    private AlbumDTO albumDTO;

    @BeforeEach
    public void setUp() {
        artist = new Artist();
        artist.setArtistId(1);
        artist.setArtistName("Arty");

        album = new Album();
        album.setAlbumId(1);
        album.setTitle("Test Album");
        album.setReleaseYear(2022);
        album.setArtist(artist);

        albumDTO = new AlbumDTO(1, "Test Album", 2022, 1);
    }

    @Test
    public void testSaveAlbum_Success() {
        when(artistRepository.findById(1)).thenReturn(Optional.of(artist));
        when(albumRepository.save(any(Album.class))).thenReturn(album);

        AlbumDTO savedAlbumDTO = albumService.saveAlbum(albumDTO);

        assertEquals(albumDTO, savedAlbumDTO);
    }

    @Test
    public void testSaveAlbum_ArtistNotFound() {
        when(artistRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> albumService.saveAlbum(albumDTO));
    }

    @Test
    public void testFindAlbumById_Success() {
        when(albumRepository.findById(1)).thenReturn(Optional.of(album));

        AlbumDTO foundAlbumDTO = albumService.findAlbumById(1);

        assertEquals(albumDTO, foundAlbumDTO);
    }

    @Test
    public void testFindAlbumById_NotFound() {
        when(albumRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> albumService.findAlbumById(1));
    }

    @Test
    public void testFindAllAlbums() {
        when(albumRepository.findAll()).thenReturn(List.of(album));

        List<AlbumDTO> albums = albumService.findAllAlbums();

        assertEquals(1, albums.size());
        assertEquals(albumDTO, albums.get(0));
    }

    @Test
    public void testDeleteAlbum_Success() {
        when(albumRepository.existsById(1)).thenReturn(true);
        doNothing().when(albumRepository).deleteById(1);

        assertDoesNotThrow(() -> albumService.deleteAlbum(1));
    }

    @Test
    public void testDeleteAlbum_NotFound() {
        when(albumRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> albumService.deleteAlbum(1));
    }

    @Test
    public void testFindAllAlbumsByArtist() {
        when(albumRepository.findByArtist_ArtistName("Arty")).thenReturn(List.of(album));

        List<AlbumDTO> albums = albumService.findAllAlbumsByArtist("Arty");

        assertEquals(1, albums.size());
        assertEquals(albumDTO, albums.get(0));
    }

    @Test
    public void testFindAlbumsByYear() {
        when(albumRepository.findByReleaseYear(2022)).thenReturn(List.of(album));

        List<AlbumDTO> albums = albumService.findAlbumsByYear(2022);

        assertEquals(1, albums.size());
        assertEquals(albumDTO, albums.get(0));
    }

    @Test
    public void testFindAlbumsByTitle() {
        when(albumRepository.findByTitle("Test Album")).thenReturn(List.of(album));

        List<AlbumDTO> albums = albumService.findAlbumsByTitle("Test Album");

        assertEquals(1, albums.size());
        assertEquals(albumDTO, albums.get(0));
    }
}