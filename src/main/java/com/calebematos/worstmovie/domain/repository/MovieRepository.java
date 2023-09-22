package com.calebematos.worstmovie.domain.repository;

import com.calebematos.worstmovie.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
