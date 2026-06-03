package com.aliyaman.libraryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {

    private long totalUsers;
    private long totalBooks;
    private long activeLoans;
    private long overdueLoans;

    private List<UserDto> latestUsers;
    private List<BookDto> latestBooks;
    private List<LatestAnnouncementDto> latestAnnouncements;
}
