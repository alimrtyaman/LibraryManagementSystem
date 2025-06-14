package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.BookDto;

import java.util.List;

public interface IBookService {

    public BookDto findBookById(Long id);

    public List<BookDto> gellAllBooks();

    public boolean deleteBookById(Long id);
}
