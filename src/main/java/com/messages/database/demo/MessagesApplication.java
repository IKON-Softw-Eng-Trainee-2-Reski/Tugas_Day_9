package com.messages.database.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.messages.database.demo")
public class MessagesApplication {
  public static void main(String[] args) {
    SpringApplication.run(MessagesApplication.class, args);
  }
}
