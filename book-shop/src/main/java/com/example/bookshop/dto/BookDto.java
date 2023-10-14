package com.example.bookshop.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record BookDto (
        int id,
        String title,
        double price,
        String author,
        String imgUrl,
        LocalDate publishedDate,
        String category
){

}
