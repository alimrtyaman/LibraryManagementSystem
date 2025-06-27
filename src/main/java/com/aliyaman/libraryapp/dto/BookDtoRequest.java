package com.aliyaman.libraryapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoRequest {




    private String title;

    private String description;

    private String author;

    private Integer stock;

    private Long isbn;

    private Long categoryId;
}
