package com.aliyaman.libraryapp.controller.impl;

import com.aliyaman.libraryapp.controller.IRestBookController;
import com.aliyaman.libraryapp.dto.BookDto;
import com.aliyaman.libraryapp.service.IBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/book")
public class RestBookControllerImpl implements IRestBookController {

    private IBookService iBookService;

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

    @DeleteMapping(path = "{id}")
    @Override
    public boolean deleteBookById(@PathVariable Long id) {
        return iBookService.deleteBookById(id);
    }
}
