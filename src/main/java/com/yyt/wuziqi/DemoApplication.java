package com.yyt.wuziqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        new LoseFrame().create();
        new Qipan().create();
        SpringApplication.run(DemoApplication.class, args);

    }

}
