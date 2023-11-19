package com.example.music.service;

import com.example.music.domain.Song;
import com.example.music.repository.SongRepository;
import com.example.music.repository.BandRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class MusicServiceTest {

    @Mock
    private BandRepository bandRepository;

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private MusicService musicService;

    public MusicServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByName() {
        String bandName = "Metallica";
        Song mockBand = new Song(1L, bandName, "Metal", 1981, "Metallica", "Album1", "120", "spotifyId1", "shortName1", "3:30");

        when(songRepository.findByName(bandName)).thenReturn(mockBand);

        Song result = musicService.findByName(bandName);

        assertNotNull(result);
        assertEquals(bandName, result.getName());
    }

    @Test
    void findBandByGenreNotEmpty() {
        String genre = "Metal";
        List<Song> mockBands = Arrays.asList(new Song(1L, "Band1", genre, 2000, "Artist1", "Album1", "120", "spotifyId1", "shortName1", "3:30"),
                new Song(2L, "Band2", genre, 2005, "Artist2", "Album2", "130", "spotifyId2", "shortName2", "4:00"));

        when(songRepository.findByGenre(genre)).thenReturn(mockBands);

        ResponseEntity<List<Song>> result = musicService.findBandByGenre(genre);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockBands, result.getBody());
    }

    @Test
    void findBandByGenreEmpty() {
        String genre = "NonExistingGenre";

        when(songRepository.findByGenre(genre)).thenReturn(Arrays.asList());

        ResponseEntity<List<Song>> result = musicService.findBandByGenre(genre);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    // Add more tests for the remaining methods...

    // Remember to test saveBand, saveSong, deleteSong, deleteBand, updateBand, and updateSong methods.
}
