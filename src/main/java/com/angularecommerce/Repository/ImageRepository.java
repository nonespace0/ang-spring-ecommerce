package com.angularecommerce.Repository;

import com.angularecommerce.Model.ImageUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageUpload, Integer> {
    Optional<ImageUpload> findByName(String name);
}
