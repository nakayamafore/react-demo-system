package com.example.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DemoAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoAppApplication.class, args);
  }

}
