package com.example.music.repository;

import com.example.music.domain.Band;
import com.example.music.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Song findByName(String name);

    List<Song> findByGenre(String genre);
}
