package com.aliyaman.libraryapp.mapper;

import com.aliyaman.libraryapp.dto.BookDto;
import com.aliyaman.libraryapp.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    BookDto toDto(Book book);
}
