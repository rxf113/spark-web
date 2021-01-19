package com.rxf113.sparkdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SparkDemoApplication {

    public static void main(String[] args) {

        try {
            SpringApplication.run(SparkDemoApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: handle exception
        }
    }

}
