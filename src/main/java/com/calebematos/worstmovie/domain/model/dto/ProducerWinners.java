package com.calebematos.worstmovie.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProducerWinners {

    private String producerName;
    private Integer eventYear;

}
