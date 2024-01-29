package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.entity.announces.AnnouncesLog;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnnouncesLogRepository extends JpaRepository<AnnouncesLog,Integer> {

   Optional<AnnouncesLog> findByIdAnnounce(Integer idAnnounce);
}
