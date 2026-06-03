package com.aliyaman.libraryapp.dto;

import com.aliyaman.libraryapp.entity.Book;
import com.aliyaman.libraryapp.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {

    private Long id;

    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;

    private boolean returned;
    private boolean overdue;

    // USER INFO
    private Long userId;
    private String userName;
    private String userEmail;

    // BOOK INFO
    private Long bookId;
    private String bookTitle;
}
