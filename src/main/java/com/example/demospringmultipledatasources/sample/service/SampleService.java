package com.example.demospringmultipledatasources.sample.service;

import com.example.demospringmultipledatasources.sample.repository.Sample;
import com.example.demospringmultipledatasources.sample.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SampleService {

    @Autowired
    SampleRepository sampleRepository;

    public void save() {
        System.out.println("#######sample");
        sampleRepository.save(new Sample("test", null));
    }
}
