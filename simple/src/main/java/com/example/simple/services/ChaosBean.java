package com.example.simple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChaosBean {

    @Autowired
    public ChaosBean(
            @Value("${app.secret:''}") String secret,
            @Value("${app.secret2:''}") String secret2
    ) {
        System.out.println("I do nothing, but spring instantiate me anyway");
        System.out.println("Ok, it is not true, I display secret values");
        System.out.println("Secret : " + secret);
        System.out.println("Secret2: " + secret2);
    }
}
