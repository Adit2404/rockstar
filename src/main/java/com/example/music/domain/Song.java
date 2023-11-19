package com.example.music.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Song information.")
public class Song {

        @Id
        @JsonProperty("Id")
        private  Long id;
        @JsonProperty("Name")
        private  String name;
        @JsonProperty("Genre")
        private  String genre;
        @JsonProperty("Year")
        private  Integer releaseYear;
        @JsonProperty("Artist")
        private  String artist;
        @JsonProperty("Album")
        private  String album;
        @JsonProperty("Bpm")
        private  String bpm;
        @JsonProperty("SpotifyId")
        private  String spotifyId;
        @JsonProperty("Shortname")
        private  String shortName;
        @JsonProperty("Duration")
        private  String duration;

}


