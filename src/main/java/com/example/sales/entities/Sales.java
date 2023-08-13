package com.example.sales.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String item;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date dot;

    private double amount;
}

