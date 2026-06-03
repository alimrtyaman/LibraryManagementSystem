package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.AnnouncementRequest;
import com.aliyaman.libraryapp.dto.AnnouncementResponse;
import com.aliyaman.libraryapp.entity.Announcement;
import com.aliyaman.libraryapp.repository.AnnouncementRepository;
import com.aliyaman.libraryapp.service.IAnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements IAnnouncementService {

    private final AnnouncementRepository repository;

    @Override
    public AnnouncementResponse create(AnnouncementRequest request) {
        Announcement a = Announcement.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .active(request.isActive())
                .build();

        Announcement saved = repository.save(a);
        return toResponse(saved);
    }

    @Override
    public List<AnnouncementResponse> getAll() {
        return repository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public AnnouncementResponse update(Long id, AnnouncementRequest request) {
        Announcement a = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found with id: " + id));

        a.setTitle(request.getTitle());
        a.setContent(request.getContent());
        a.setActive(request.isActive());

        Announcement updated = repository.save(a);
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Announcement not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<AnnouncementResponse> getActive() {
        return repository.findAllByOrderByCreatedAtDesc()
                .stream()
                .filter(Announcement::isActive)      // sadece active olanlar
                .map(this::toResponse)
                .toList();
    }

    private AnnouncementResponse toResponse(Announcement a) {
        AnnouncementResponse r = new AnnouncementResponse();
        r.setId(a.getId());
        r.setTitle(a.getTitle());
        r.setContent(a.getContent());
        r.setActive(a.isActive());
        r.setCreatedAt(a.getCreatedAt());
        return r;
    }
}
