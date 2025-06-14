package com.aliyaman.libraryapp.controller;

import com.aliyaman.libraryapp.dto.BookDto;

import java.util.List;

public interface IRestBookController {

    public BookDto findBookById(Long id);

    public List<BookDto> gellAllBooks();

    public boolean deleteBookById(Long id);
}
