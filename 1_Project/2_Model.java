package com.tenex.catalogue.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="books")
public class Book {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(name = "title")
    private String title;

    @NotBlank
    @NotNull
    @Column(name = "author")
    private String author;

    @Column(name = "price")
    @NotNull
    @NotBlank
    private Double price;

    @Column(name = "stock")
    @NotBlank
    @NotNull
    @Min(0)
    private Integer Stock;


    @NotBlank
    @NotNull
    @Column(name = "book_identifier", unique = true)
    private String bookIdentifier;



}


