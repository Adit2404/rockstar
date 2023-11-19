package com.example.music.loader;

import com.example.music.domain.Band;
import com.example.music.domain.Song;
import com.example.music.repository.SongRepository;
import com.example.music.repository.BandRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
class DataLoader implements CommandLineRunner {

    private ObjectMapper mapper;

    private BandRepository bandRepository;
    private SongRepository songRepository;

    public DataLoader(ObjectMapper mapper, BandRepository bandRepository, SongRepository songRepository) {
        this.mapper = mapper;
        this.bandRepository = bandRepository;
        this.songRepository = songRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading data...");
        List<Song> songs = new ArrayList<>();
        List<Band> bands = new ArrayList<>();

        loadSongs(songs);
        songRepository.saveAll(songs);

        loadBands(bands);
        bandRepository.saveAll(bands);
    }

    private void loadSongs(List<Song> songs) {
        JsonNode json;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/songs.json")) {
            json = mapper.readValue(inputStream, JsonNode.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data", e);
        }

        for (JsonNode node : json) {
            Song song = createSong(node);
            if ("Metal".equalsIgnoreCase(song.getGenre()) || song.getReleaseYear() < 2016) {
                songs.add(song);
            }
        }
    }

    private void loadBands(List<Band> songs) {
        JsonNode json;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/artists.json")) {
            json = mapper.readValue(inputStream, JsonNode.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data", e);
        }

        for (JsonNode node : json) {
           songs.add(createBand(node));
        }

    }

    private Band createBand(JsonNode edge) {
        long id = edge.get("Id").asLong();
        String name = edge.get("Name").asText();

        return new Band(id, name);
    }

    private Song createSong(JsonNode node) {
        long id = node.get("Id").asLong();
        String name = node.get("Name").asText();
        String genre = node.get("Genre").asText();
        Integer releaseYear = node.get("Year").asInt();
        String artist = node.get("Artist").asText();
        String album = node.get("Album").asText();
        String bpm = node.get("Bpm").asText();
        String spotifyId = node.get("SpotifyId").asText();
        String shortName = node.get("Shortname").asText();
        String duration = node.get("Duration").asText();

        return new Song(id, name, genre, releaseYear, artist, album, bpm, spotifyId, shortName, duration);
    }
}
