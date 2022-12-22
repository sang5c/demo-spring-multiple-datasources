package com.example.demospringmultipledatasources.second;

import com.example.demospringmultipledatasources.second.repository.Second;
import com.example.demospringmultipledatasources.second.repository.SecondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecondService {

    @Autowired
    private SecondRepository secondRepository;

    @Transactional("secondTransactionManager")
    public void save() {
        System.out.println("CALL!!!!!!");
        secondRepository.save(new Second("test"));
    }
}
