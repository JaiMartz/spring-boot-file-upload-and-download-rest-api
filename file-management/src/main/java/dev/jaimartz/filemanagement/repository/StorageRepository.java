package dev.jaimartz.filemanagement.repository;

import dev.jaimartz.filemanagement.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String fileName);
}
