package com.calebematos.worstmovie.infrastructure.repository;

import com.calebematos.worstmovie.domain.model.dto.ProducerWinners;
import com.calebematos.worstmovie.domain.repository.ProducerRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProducerRepositoryImpl implements ProducerRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<ProducerWinners> findWinners() {

        List<ProducerWinners> producerWinnersList = new ArrayList<>();

        String sql = """
                WITH WinningProducers AS (
                  SELECT p.id
                  FROM Movie m
                  JOIN Movie_Producer mp ON mp.movie_id = m.id
                  JOIN Producer p ON p.id = mp.producer_id
                  WHERE m.winner = true
                  GROUP BY p.id
                  HAVING COUNT(m.id) > 1
                )
                                
                SELECT P.name AS producer_name, m.event_year
                FROM Producer AS P
                JOIN Movie_Producer mp ON mp.producer_id = P.id
                JOIN Movie m ON m.id = mp.movie_id AND m.winner = true
                JOIN WinningProducers AS subquery ON P.id = subquery.id
                ORDER BY m.event_year;
                """;
        Query query = manager.createNativeQuery(sql);

        List<Object[]> resultList = query.getResultList();

        producerWinnersList = resultList.stream()
                .map(r -> new ProducerWinners((String) r[0], (Integer) r[1]))
                .toList();

        return producerWinnersList;
    }
}
