package com.example.music.repository;

import com.example.music.domain.Song;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Test
    void findByName() {
        String bandName = "Metallica";
        Song savedBand = songRepository.save(new Song(111L, bandName, "Metal", 1981, "Metallica", "Album1", "120", "spotifyId1", "shortName1", "3:30"));

        Song foundBand = songRepository.findByName(bandName);

        assertNotNull(foundBand);
        assertEquals(savedBand.getId(), foundBand.getId());
        assertEquals(bandName, foundBand.getName());
    }

    @Test
    void findByGenre() {
        String genre = "Metal";
        Song band1 = new Song(111L, "Band1", genre, 2000, "Artist1", "Album1", "120", "spotifyId1", "shortName1", "3:30");
        Song band2 = new Song(222L, "Band2", genre, 2005, "Artist2", "Album2", "130", "spotifyId2", "shortName2", "4:00");
        songRepository.saveAll(List.of(band1, band2));

        List<Song> foundBands = songRepository.findByGenre(genre);

        assertEquals(2, foundBands.size());
        assertTrue(foundBands.contains(band1));
        assertTrue(foundBands.contains(band2));
    }

    @Test
    void findByGenreEmpty() {
        String nonExistingGenre = "NonExistingGenre";

        List<Song> foundBands = songRepository.findByGenre(nonExistingGenre);

        assertTrue(foundBands.isEmpty());
    }
}

