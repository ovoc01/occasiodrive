
package com.cloud.ventevoiture.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloud.ventevoiture.model.entity.car.version.Version;

@Repository
public interface VersionRepository extends JpaRepository<Version,Integer> {

   @Query(nativeQuery = true, value = "SELECT * FROM v_motorisation_version WHERE id_motorisation = :motorisationId")
   List<Version> findVersionByMotorisationId(@Param("motorisationId") Integer motorisationId);
}
