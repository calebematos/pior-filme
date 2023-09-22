package com.calebematos.worstmovie.api.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Prize {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

}
