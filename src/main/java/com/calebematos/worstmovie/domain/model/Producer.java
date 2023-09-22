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
public class Producer {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "producers")
    private List<Movie> movies;
}
