package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.entity.category.Category;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "Select * from category where id_category = :id_category" ,nativeQuery = true)
    public Category findOne(@Param("id_category") int id_category);

    @Query(value = "delete from category where id_category = :id_category", nativeQuery = true)
    public void deleteById_category(@Param("id_category") int id_category);

}
