package com.example.demospringmultipledatasources.sample;

import com.example.demospringmultipledatasources.sample.repository.Sample;
import com.example.demospringmultipledatasources.sample.repository.SampleRepository;
import com.example.demospringmultipledatasources.sample.repository.SampleType;
import com.example.demospringmultipledatasources.sample.service.SampleService;
import com.example.demospringmultipledatasources.second.SecondService;
import com.example.demospringmultipledatasources.second.repository.Second;
import com.example.demospringmultipledatasources.second.repository.SecondRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class SampleController {

    @Autowired
    private final SampleService sampleService;

    @Autowired
    private final SecondService secondService;

    @GetMapping("/")
    public String index() {
        sampleService.save();
        secondService.save(); // TODO: JTA transaction 찾아보기
        return "Hello World!";
    }
}
