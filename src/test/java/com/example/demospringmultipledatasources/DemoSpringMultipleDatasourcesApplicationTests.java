package com.example.demospringmultipledatasources;

import com.example.demospringmultipledatasources.sample.repository.Sample;
import com.example.demospringmultipledatasources.sample.repository.SampleRepository;
import com.example.demospringmultipledatasources.sample.repository.SampleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoSpringMultipleDatasourcesApplicationTests {

    @Autowired
    private SampleRepository sampleRepository;

    @Test
    void contextLoads() {
        sampleRepository.save(new Sample("test", SampleType.A));
    }

}
