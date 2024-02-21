package com.back.PoopKings.repositories;

import com.back.PoopKings.models.database.PodiumMO;
import com.back.PoopKings.models.database.PodiumPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PodiumRepository extends JpaRepository<PodiumMO, PodiumPK> {

    List<PodiumMO> findAllByChatId (Long id);
}
