package com.angularecommerce.Repository;

import com.angularecommerce.Model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
}
