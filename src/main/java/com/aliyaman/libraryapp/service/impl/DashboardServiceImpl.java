package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.AnnouncementResponse;
import com.aliyaman.libraryapp.dto.BookDto;
import com.aliyaman.libraryapp.dto.DashboardResponse;
import com.aliyaman.libraryapp.dto.LatestAnnouncementDto;
import com.aliyaman.libraryapp.entity.Announcement;
import com.aliyaman.libraryapp.mapper.BookMapper;
import com.aliyaman.libraryapp.mapper.UserMapper;
import com.aliyaman.libraryapp.repository.AnnouncementRepository;
import com.aliyaman.libraryapp.repository.BookRepository;
import com.aliyaman.libraryapp.repository.LoanRepository;
import com.aliyaman.libraryapp.repository.UserRepository;
import com.aliyaman.libraryapp.service.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardServiceImpl implements IDashboardService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final AnnouncementRepository announcementRepository;

    private final UserMapper userMapper;     // ✔ mapper var
    private final BookMapper bookMapper;     // ✔ mapper var

    @Autowired
    public DashboardServiceImpl(UserRepository userRepository,
                                BookRepository bookRepository,
                                LoanRepository loanRepository,
                                AnnouncementRepository announcementRepository,
                                UserMapper userMapper,
                                BookMapper bookMapper) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.announcementRepository = announcementRepository;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
    }

    @Override
    public DashboardResponse getAdminDashboard() {

        DashboardResponse r = new DashboardResponse();

        r.setTotalUsers(userRepository.count());
        r.setTotalBooks(bookRepository.count());
        r.setActiveLoans(loanRepository.countByIsReturnedFalse());
        r.setOverdueLoans(
                loanRepository.countByDueDateBeforeAndIsReturnedFalse(LocalDateTime.now())
        );

        r.setLatestUsers(
                userRepository.findTop3ByOrderByIdDesc()
                        .stream()
                        .map(userMapper::toDto)
                        .toList()
        );

        r.setLatestBooks(
                bookRepository.findTop3ByOrderByIdDesc()   // veya IdDesc
                        .stream()
                        .map(bookMapper::toDto)
                        .toList()
        );

        r.setLatestAnnouncements(
                announcementRepository.findTop3ByActiveTrueOrderByCreatedAtDesc()
                        .stream()
                        .map(this::mapAnnouncement)
                        .toList()
        );

        return r;
    }

    private LatestAnnouncementDto mapAnnouncement(Announcement a) {
        LatestAnnouncementDto dto = new LatestAnnouncementDto();
        dto.setTitle(a.getTitle());
        dto.setCreatedAt(a.getCreatedAt());
        dto.setActive(a.isActive());
        return dto;
    }
}
