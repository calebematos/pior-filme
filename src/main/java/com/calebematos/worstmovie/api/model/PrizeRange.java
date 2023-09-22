package com.calebematos.worstmovie.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PrizeRange {

    private List<Prize> min;
    private List<Prize> max;


}
