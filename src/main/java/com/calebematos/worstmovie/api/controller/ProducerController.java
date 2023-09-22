package com.calebematos.worstmovie.api.controller;

import com.calebematos.worstmovie.api.model.IntervalWinner;
import com.calebematos.worstmovie.domain.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producers")
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping("/winners")
    public IntervalWinner findWinners(){
        return producerService.findWinnersWithMinAndMaxInterval();
    }

}
