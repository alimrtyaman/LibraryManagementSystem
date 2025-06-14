package com.aliyaman.libraryapp.dto;

import com.aliyaman.libraryapp.entity.Category;
import com.aliyaman.libraryapp.entity.Loan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {


    private String title;

    private String description;

    private String author;

    private Integer stock;

    private boolean status;

    private LocalDateTime addedDate;

    private Category category;

}
