package com.example.music.repository;

import com.example.music.domain.Band;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandRepository extends JpaRepository<Band, Long> {

    Band findByName(String name);
}
