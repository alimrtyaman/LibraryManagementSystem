package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.BookDto;
import com.aliyaman.libraryapp.dto.BookDtoRequest;

import java.util.List;

public interface IBookService {

    public BookDto findBookById(Long id);

    public List<BookDto> gellAllBooks();

    public boolean deleteBookById(Long id);

    public BookDto findBookByName(String title);

    public BookDto saveBook(BookDtoRequest request);
}
