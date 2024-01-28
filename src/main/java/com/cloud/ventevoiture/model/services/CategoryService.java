package com.cloud.ventevoiture.model.services;

import com.cloud.ventevoiture.model.entity.car.Motorisation;
import com.cloud.ventevoiture.model.entity.category.Category;
import com.cloud.ventevoiture.model.entity.model.ModelCategory;
import com.cloud.ventevoiture.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Motorisation> getMotorisationsForCategory(int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        
        if (!category.isPresent()) {
            return Collections.emptyList();
        }

        List<Motorisation> motorisations = new LinkedList<>();
        Set<ModelCategory> modelCategories = category.get().getModelCategories();
        for (ModelCategory modelCategory : modelCategories) {
            motorisations.addAll(modelCategory.getMotorisations());
        }
        return motorisations;
    }
}
