package com.example.demospringmultipledatasources.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SampleController {

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }
}
