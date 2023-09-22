package com.calebematos.worstmovie.domain.service;

import com.calebematos.worstmovie.api.model.Winner;
import com.calebematos.worstmovie.api.model.IntervalWinner;
import com.calebematos.worstmovie.domain.model.dto.ProducerWinners;
import com.calebematos.worstmovie.domain.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final ProducerRepository producerRepository;

    public IntervalWinner findWinnersWithMinAndMaxInterval() {

        List<ProducerWinners> winners = producerRepository.findWinners();
        Map<String, List<Integer>> winnersGroupByName = winners.stream()
                .collect(groupingBy(ProducerWinners::getProducerName, mapping(ProducerWinners::getEventYear, Collectors.toList())));

        List<Winner> winnerList = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> winner : winnersGroupByName.entrySet()) {
            winnerList.addAll(getWinners(winner.getKey(), winner.getValue()));
        }

        winnerList.sort(Comparator.comparing(Winner::getInterval));

        Integer minimumInterval = winnerList.get(0).getInterval();
        Integer maximumInterval = winnerList.get(winnerList.size() - 1).getInterval();

        List<Winner> min = winnerList.stream().filter(p -> Objects.equals(p.getInterval(), minimumInterval)).toList();
        List<Winner> max = winnerList.stream().filter(p -> Objects.equals(p.getInterval(), maximumInterval)).toList();


        return IntervalWinner.builder()
                .min(min)
                .max(max)
                .build();
    }

    private List<Winner> getWinners(String producerName, List<Integer> years) {
        List<Winner> winners = new ArrayList<>();
        for (int i = 0; i < years.size() - 1; i++) {
            Integer previousWin = years.get(i);
            Integer followingWin = years.get(i + 1);
            winners.add(Winner.builder()
                    .producer(producerName)
                    .previousWin(previousWin)
                    .followingWin(followingWin)
                    .interval(followingWin - previousWin)
                    .build());
        }
        return winners;
    }
}
