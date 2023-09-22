package com.calebematos.worstmovie.domain.service;

import com.calebematos.worstmovie.api.model.Prize;
import com.calebematos.worstmovie.api.model.PrizeRange;
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
public class PrizeRangeService {

    private final ProducerRepository producerRepository;

    public PrizeRange findPrizeRangeMinAndMax() {

        List<ProducerWinners> winners = producerRepository.findWinners();
        Map<String, List<Integer>> winnersGroupByName = winners.stream()
                .collect(groupingBy(ProducerWinners::getProducerName, mapping(ProducerWinners::getEventYear, Collectors.toList())));

        List<Prize> prizeList = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> winner : winnersGroupByName.entrySet()) {
            prizeList.addAll(getPrizes(winner.getKey(), winner.getValue()));
        }

        prizeList.sort(Comparator.comparing(Prize::getInterval));

        Integer minimumInterval = prizeList.get(0).getInterval();
        Integer maximumInterval = prizeList.get(prizeList.size() - 1).getInterval();

        List<Prize> min = prizeList.stream().filter(p -> Objects.equals(p.getInterval(), minimumInterval)).toList();
        List<Prize> max = prizeList.stream().filter(p -> Objects.equals(p.getInterval(), maximumInterval)).toList();


        return PrizeRange.builder()
                .min(min)
                .max(max)
                .build();
    }

    private List<Prize> getPrizes(String producerName, List<Integer> years) {
        List<Prize> prizes = new ArrayList<>();
        for (int i = 0; i < years.size() - 1; i++) {
            Integer previousWin = years.get(i);
            Integer followingWin = years.get(i + 1);
            prizes.add(Prize.builder()
                    .producer(producerName)
                    .previousWin(previousWin)
                    .followingWin(followingWin)
                    .interval(followingWin - previousWin)
                    .build());
        }
        return prizes;
    }
}
