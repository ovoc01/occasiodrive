package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.entity.brand.Brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query(value = "Select * from brand where id_brand = :id_brand" ,nativeQuery = true)
    public Brand findOne(@Param("id_brand") int id_brand);

    @Query(value = "delete from brand where id_brand = :id_brand", nativeQuery = true)
    public void deleteById_brand(@Param("id_brand") int id_brand);

    public Page<Brand> findAll(Pageable pageable);


}
