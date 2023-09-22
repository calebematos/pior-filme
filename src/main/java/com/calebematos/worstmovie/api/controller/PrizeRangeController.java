package com.calebematos.worstmovie.api.controller;

import com.calebematos.worstmovie.api.model.PrizeRange;
import com.calebematos.worstmovie.domain.service.PrizeRangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ranges")
public class PrizeRangeController {

    private final PrizeRangeService prizeRangeService;

    @GetMapping
    public PrizeRange findPrizeRage(){

        return prizeRangeService.findPrizeRangeMinAndMax();
    }

}
