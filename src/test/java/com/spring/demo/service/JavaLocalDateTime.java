package com.spring.demo.service;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Zhou Gang
 * @date 2023/2/4 18:08
 */
public class JavaLocalDateTime {

    public static void main(String[] args) {
//        LocalDate now = LocalDate.now();
//        System.out.println(now);

        LocalDate l = LocalDate.now(Clock.systemUTC());
        System.out.println(Clock.systemDefaultZone());

        LocalDateTime now = LocalDateTime.now();
        System.out.println();

    }
}
