package com.aliyaman.libraryapp.repository;

import com.aliyaman.libraryapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {

    Optional<Category> findById(Long id);
}
