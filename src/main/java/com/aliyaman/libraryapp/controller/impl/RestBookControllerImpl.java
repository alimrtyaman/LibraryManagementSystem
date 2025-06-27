package com.aliyaman.libraryapp.controller.impl;

import com.aliyaman.libraryapp.controller.IRestBookController;
import com.aliyaman.libraryapp.dto.BookDto;
import com.aliyaman.libraryapp.dto.BookDtoRequest;
import com.aliyaman.libraryapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/book")
public class RestBookControllerImpl implements IRestBookController {

    private final IBookService iBookService;

    @Autowired
    public RestBookControllerImpl(IBookService iBookService){
        this.iBookService = iBookService;
    }

    @GetMapping(path = {"id"})
    @Override
    public BookDto findBookById(@PathVariable Long id) {
        return iBookService.findBookById(id);
    }

    @GetMapping
    @Override
    public List<BookDto> gellAllBooks() {
        return iBookService.gellAllBooks();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "{id}")
    @Override
    public boolean deleteBookById(@PathVariable Long id) {
        return iBookService.deleteBookById(id);
    }

    @PostMapping
    @Override
    public BookDto findBookByName(@RequestParam String title) {
        return iBookService.findBookByName(title);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save-book")
    @Override
    public BookDto saveBook(@RequestBody BookDtoRequest request) {
        return iBookService.saveBook(request);
    }
}
