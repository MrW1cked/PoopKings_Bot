package com.back.poopkings.repositories;

import com.back.poopkings.models.database.PodiumMO;
import com.back.poopkings.models.database.PodiumPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PodiumRepository extends JpaRepository<PodiumMO, PodiumPK> {

    List<PodiumMO> findAllByChatId (Long id);
}
