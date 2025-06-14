package com.aliyaman.libraryapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "status")
    private boolean status;

    @Column(name = "isbn")
    private Long isbn;

    @Column(name = "added_date")
    private LocalDateTime addedDate;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Loan> loans = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
