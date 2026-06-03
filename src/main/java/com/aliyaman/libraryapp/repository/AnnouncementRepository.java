package com.aliyaman.libraryapp.repository;

import com.aliyaman.libraryapp.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findAllByOrderByCreatedAtDesc();

    long countByActiveTrue();

    List<Announcement> findTop3ByActiveTrueOrderByCreatedAtDesc();

}
