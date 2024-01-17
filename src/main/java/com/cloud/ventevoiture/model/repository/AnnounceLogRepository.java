package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.entity.announces.AnnouncesLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnounceLogRepository extends JpaRepository<AnnouncesLog,Integer> {
}
