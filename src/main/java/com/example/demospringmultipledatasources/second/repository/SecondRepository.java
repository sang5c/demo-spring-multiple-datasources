package com.example.demospringmultipledatasources.second.repository;

import com.example.demospringmultipledatasources.sample.repository.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondRepository extends JpaRepository<Sample, Long> {
}
