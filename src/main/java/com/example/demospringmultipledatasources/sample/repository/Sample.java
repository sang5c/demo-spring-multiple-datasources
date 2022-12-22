package com.example.demospringmultipledatasources.sample.repository;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sample {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "char")
    private SampleType type;

    public Sample() {
    }

    public Sample(String name, SampleType type) {
        this.name = name;
        this.type = type;
    }
}
