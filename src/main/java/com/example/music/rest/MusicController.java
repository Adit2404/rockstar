package com.example.music.rest;


import com.example.music.domain.Band;
import com.example.music.domain.Song;
import com.example.music.exception.ValidationException;
import com.example.music.service.MusicService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Music API", description = "Rest Controller for Music API")
public class MusicController {


    private final MusicService musicService;

    @GetMapping("/band/{name}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Band found"),
            @ApiResponse(responseCode = "400", description = "Invalid Metal.", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\n" +
                            "    \"status\": 400,\n" +
                            "    \"detail\": \"Band with 'Metal' genre not found.\",\n" +
                            "    \"path\": \"/band/{bandName}\",\n" +
                            "    \"message\": null" +
                            "}"
                    )))})
    public Song findBandByName(@PathVariable String name) {
        return musicService.findByName(name);

    }

    @GetMapping("/song/{genre}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Band found"),
            @ApiResponse(responseCode = "400", description = "Invalid Metal.", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\n" +
                            "    \"status\": 400,\n" +
                            "    \"detail\": \"Band with 'Metal' genre not found.\",\n" +
                            "    \"path\": \"/band/{bandName}\",\n" +
                            "    \"message\": null" +
                            "}"
                    )))})
    public ResponseEntity<List<Song>> findSongByGenre(@PathVariable String genre) {
        return musicService.findBandByGenre(genre);

    }

    @PostMapping("/addband")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New Band added"),
            @ApiResponse(responseCode = "400", description = "Something went wrong..", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\n" +
                            "    \"status\": 400,\n" +
                            "    \"detail\": \"Something went wrong..\",\n" +
                            "    \"path\": \"/band/{bandName}\",\n" +
                            "    \"message\": null" +
                            "}"
                    )))})
    public ResponseEntity addBand(@RequestBody @Valid Band band) throws ValidationException {
        return musicService.saveBand(band);
    }

    @PostMapping("/addsong")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New Band added"),
            @ApiResponse(responseCode = "400", description = "Something went wrong..", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\n" +
                            "    \"status\": 400,\n" +
                            "    \"detail\": \"Something went wrong..\",\n" +
                            "    \"path\": \"/band/{bandName}\",\n" +
                            "    \"message\": null" +
                            "}"
                    )))})
    public Song addSong(@RequestBody  @Valid Song song) {
        return musicService.saveSong(song);
    }

    @DeleteMapping("/artist/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Song Deleted "),
            @ApiResponse(responseCode = "400", description = "Something went wrong..", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\n" +
                            "    \"status\": 400,\n" +
                            "    \"detail\": \"Something went wrong..\",\n" +
                            "    \"path\": \"/artist/{SongId}\",\n" +
                            "    \"message\": null" +
                            "}"
                    )))})
    void deleteSongbyId(@PathVariable Long id) {
        musicService.deleteSong(id);
    }
    @DeleteMapping("/band/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Band Deleted "),
            @ApiResponse(responseCode = "400", description = "Something went wrong..", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\n" +
                            "    \"status\": 400,\n" +
                            "    \"detail\": \"Something went wrong..\",\n" +
                            "    \"path\": \"/band/{SongId}\",\n" +
                            "    \"message\": null" +
                            "}"
                    )))})
    void deleteByBandId(@PathVariable Long id) {
        musicService.deleteBand(id);
    }

    @PutMapping("/artist/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Song Updated"),
            @ApiResponse(responseCode = "400", description = "Something went wrong..", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\n" +
                            "    \"status\": 400,\n" +
                            "    \"detail\": \"Something went wrong..\",\n" +
                            "    \"path\": \"/artist/{SongId}\",\n" +
                            "    \"message\": null" +
                            "}"
                    )))})
    public Song updateSong(@PathVariable long id, @RequestBody Song song) {
        return musicService.updateSong(id, song);

    }

    @PutMapping("/band/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Band Updated"),
            @ApiResponse(responseCode = "400", description = "Something went wrong..", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{\n" +
                            "    \"status\": 400,\n" +
                            "    \"detail\": \"Something went wrong..\",\n" +
                            "    \"path\": \"/band/{SongId}\",\n" +
                            "    \"message\": null" +
                            "}"
                    )))})
    public Song updateBand(@PathVariable long id, @RequestBody Song band) {
        return musicService.updateBand(id, band);

    }
}
