package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query(value = "Select * from category where id_category = :id_category" ,nativeQuery = true)
    public Category findOne(@Param("id_category") int id_category);

    @Query(value = "delete from category where id_category = :id_category", nativeQuery = true)
    public void deleteById_category(@Param("id_category") int id_category);

}
