package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.AnnouncementRequest;
import com.aliyaman.libraryapp.dto.AnnouncementResponse;

import java.util.List;

public interface IAnnouncementService {

    public AnnouncementResponse create(AnnouncementRequest request);

    public List<AnnouncementResponse> getAll();

    public AnnouncementResponse update(Long id, AnnouncementRequest request);

    public void delete(Long id);

    public  List<AnnouncementResponse> getActive();
}
