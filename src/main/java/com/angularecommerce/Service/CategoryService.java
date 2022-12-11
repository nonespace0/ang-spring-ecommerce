package com.angularecommerce.Service;

import com.angularecommerce.Model.Categories;
import com.angularecommerce.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository catRepo;

    public void saveAll(Categories categories){
        catRepo.save(categories);
    }

    public List<Categories> getData(){
        return catRepo.findAll();
    }
}
