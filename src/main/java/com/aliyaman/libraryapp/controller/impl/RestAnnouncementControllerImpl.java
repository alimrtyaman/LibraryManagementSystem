package com.aliyaman.libraryapp.controller.impl;


import com.aliyaman.libraryapp.controller.IRestAnnouncementController;
import com.aliyaman.libraryapp.dto.AnnouncementRequest;
import com.aliyaman.libraryapp.dto.AnnouncementResponse;
import com.aliyaman.libraryapp.service.IAnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/announcement")

public class RestAnnouncementControllerImpl implements IRestAnnouncementController {

    private final IAnnouncementService announcementService;

    @Autowired
    public RestAnnouncementControllerImpl(IAnnouncementService announcementService) {
        this.announcementService = announcementService;
    }



    @GetMapping
    @Override
    public List<AnnouncementResponse> getActiveAnnouncements() {
        return announcementService.getActive();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    @Override
    public List<AnnouncementResponse> getAllAnnouncements() {
        return announcementService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save-announcement")
    @Override
    public AnnouncementResponse saveAnnouncement(@RequestBody AnnouncementRequest request) {
        return announcementService.create(request);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Override
    public AnnouncementResponse updateAnnouncement(@PathVariable Long id,
                                                   @RequestBody AnnouncementRequest request) {
        return announcementService.update(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public boolean deleteAnnouncement(@PathVariable Long id) {
        announcementService.delete(id);
        return true;
    }
}
