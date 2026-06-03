package com.aliyaman.libraryapp.controller;

import com.aliyaman.libraryapp.dto.AnnouncementRequest;
import com.aliyaman.libraryapp.dto.AnnouncementResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IRestAnnouncementController {

    List<AnnouncementResponse> getAllAnnouncements();

    AnnouncementResponse saveAnnouncement(AnnouncementRequest request);

    AnnouncementResponse updateAnnouncement(Long id, AnnouncementRequest request);

    boolean deleteAnnouncement(Long id);

    List<AnnouncementResponse> getActiveAnnouncements();
}
