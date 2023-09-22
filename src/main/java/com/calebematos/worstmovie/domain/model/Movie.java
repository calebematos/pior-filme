package com.calebematos.worstmovie.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Movie {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    private String studios;
    private Integer event_year;
    private Boolean winner = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name = "movie_producer",
                joinColumns = @JoinColumn(name = "movie_id"),
                inverseJoinColumns = @JoinColumn(name = "producer_id"))
    private List<Producer> producers;
}
