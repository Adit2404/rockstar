package com.example.music.service;

import com.example.music.domain.Band;
import com.example.music.domain.Song;
import com.example.music.exception.ValidationException;
import com.example.music.repository.BandRepository;
import com.example.music.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicService {

    private final BandRepository bandRepository;

    private final SongRepository songRepository;

    public Song findByName(String name) {
        return songRepository.findByName(name);
    }


    public ResponseEntity<List<Song>> findBandByGenre(String genre) {

         List <Song> songs = songRepository.findByGenre(genre);
        return songs.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(songs, HttpStatus.OK);
    }

    public ResponseEntity<Band> saveBand(Band band) throws ValidationException {

        var bandByName = bandRepository.findByName(band.getName());
        if (bandByName!= null) {
            throw new ValidationException("Band with name " + band.getName() + " already exists.");
        }
         bandRepository.save(band);
        return new ResponseEntity<>(band, HttpStatus.OK);
    }

    public Song saveSong(Song song) {
         songRepository.save(song);
        return song;
    }

    public void deleteSong(Long id) {
        var song = songRepository.findById(id);
        if (song.isPresent()) {
            songRepository.delete(song.get());
        }


    }

    public void deleteBand(long id) {
        var band =songRepository.findById(id);
        if (band.isPresent()) {
            songRepository.delete(band.get());
        }

    }

    public Song updateBand(long id, Song band) {
        Song song = songRepository.findById(id).orElse(null);
        if (song != null) {
            song.setName(band.getName());
            song.setGenre(band.getGenre());
            song.setReleaseYear(band.getReleaseYear());
            song.setArtist(band.getArtist());
            song.setAlbum(band.getAlbum());
            song.setBpm(band.getBpm());
            song.setSpotifyId(band.getSpotifyId());
            song.setShortName(band.getShortName());
            song.setDuration(band.getDuration());
            return songRepository.save(song);
        } else {
            return null;
        }
    }

    public Song updateSong(long id, Song song) {
        Song band = songRepository.findById(id).orElse(null);
        if (band != null) {
            band.setName(song.getName());
            return songRepository.save(band);
        } else {
            return null;
        }
    }
}
