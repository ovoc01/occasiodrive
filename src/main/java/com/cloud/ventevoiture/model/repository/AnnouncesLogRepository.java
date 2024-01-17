package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.entity.announces.AnnouncesLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncesLogRepository extends JpaRepository<AnnouncesLog,Integer> {
}
