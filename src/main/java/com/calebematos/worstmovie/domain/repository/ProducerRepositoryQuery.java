package com.calebematos.worstmovie.domain.repository;

import com.calebematos.worstmovie.domain.model.dto.ProducerWinners;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProducerRepositoryQuery {

    List<ProducerWinners> findWinners();
}
