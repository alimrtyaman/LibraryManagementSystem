package com.aliyaman.libraryapp.repository;

import com.aliyaman.libraryapp.dto.BookDto;
import com.aliyaman.libraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book , Long> {
    Optional<Book> findBookByTitle(String title);
}
