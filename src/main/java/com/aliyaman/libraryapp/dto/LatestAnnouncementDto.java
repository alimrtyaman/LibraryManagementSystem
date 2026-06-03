package com.aliyaman.libraryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LatestAnnouncementDto {
    private String title;
    private LocalDateTime createdAt;
    private boolean active;
}
