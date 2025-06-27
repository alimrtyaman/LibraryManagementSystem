package com.aliyaman.libraryapp.controller;

import com.aliyaman.libraryapp.dto.BookDto;
import com.aliyaman.libraryapp.dto.BookDtoRequest;

import java.util.List;

public interface IRestBookController {

    public BookDto findBookById(Long id);

    public List<BookDto> gellAllBooks();

    public boolean deleteBookById(Long id);

    public BookDto findBookByName(String title);

    public BookDto saveBook(BookDtoRequest request);

}
