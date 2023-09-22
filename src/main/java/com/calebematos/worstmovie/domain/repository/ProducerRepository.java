package com.calebematos.worstmovie.domain.repository;

import com.calebematos.worstmovie.domain.model.Producer;
import com.calebematos.worstmovie.domain.model.dto.ProducerWinners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long>, ProducerRepositoryQuery {

    Producer findByName(String name);

}
