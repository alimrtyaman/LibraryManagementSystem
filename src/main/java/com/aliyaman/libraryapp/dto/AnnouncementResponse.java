package com.aliyaman.libraryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementResponse {

    private Long id;
    private String title;
    private String content;
    private boolean active;
    private LocalDateTime createdAt;
}
