package com.calebematos.worstmovie.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Winner {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

}
