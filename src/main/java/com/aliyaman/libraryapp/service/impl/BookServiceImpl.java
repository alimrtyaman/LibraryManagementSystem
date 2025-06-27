package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.BookDto;
import com.aliyaman.libraryapp.dto.BookDtoRequest;
import com.aliyaman.libraryapp.entity.Book;
import com.aliyaman.libraryapp.entity.Category;
import com.aliyaman.libraryapp.mapper.BookMapper;
import com.aliyaman.libraryapp.repository.BookRepository;
import com.aliyaman.libraryapp.repository.CategoryRepository;
import com.aliyaman.libraryapp.service.IBookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository , BookMapper bookMapper, CategoryRepository categoryRepository){
        this.bookMapper = bookMapper;
        this.bookRepository=theBookRepository;
        this.categoryRepository = categoryRepository;
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

    @Transactional
    @Override
    public BookDto saveBook(BookDtoRequest request) {
        Book savedBook = new Book();
        savedBook.setTitle(request.getTitle());
        savedBook.setDescription(request.getDescription());
        savedBook.setAuthor(request.getAuthor());
        savedBook.setStock(request.getStock());
        savedBook.setStatus(true);
        savedBook.setIsbn(request.getIsbn());
        savedBook.setAddedDate(LocalDateTime.now());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new IllegalArgumentException("Category Not Found")
        );
        savedBook.setCategory(category);
        bookRepository.save(savedBook);
        return bookMapper.toDto(savedBook);
    }

}
