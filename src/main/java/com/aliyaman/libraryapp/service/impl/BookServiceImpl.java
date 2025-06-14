package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.BookDto;
import com.aliyaman.libraryapp.entity.Book;
import com.aliyaman.libraryapp.mapper.BookMapper;
import com.aliyaman.libraryapp.repository.BookRepository;
import com.aliyaman.libraryapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    @Autowired
    public BookServiceImpl(BookRepository theBookRepository , BookMapper bookMapper){
        this.bookMapper = bookMapper;
        this.bookRepository=theBookRepository;
    }

    @Override
    public BookDto findBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

    }

    @Override
    public List<BookDto> gellAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteBookById(Long id) {
       Book book = bookRepository.findById(id).orElseThrow(()
               -> new RuntimeException("Book not found") );
       bookRepository.deleteById(id);
        return true;
    }

    @Override
    public BookDto findBookByName(String title) {
        return bookRepository.findBookByTitle(title).map(bookMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Book not found with name " + title));
    }

}
