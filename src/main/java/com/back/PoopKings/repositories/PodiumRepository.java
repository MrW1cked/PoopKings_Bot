package com.back.PoopKings.repositories;

import com.back.PoopKings.models.PodiumMO;
import com.back.PoopKings.models.PodiumPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PodiumRepository extends JpaRepository<PodiumMO, PodiumPK> {

    List<PodiumMO> findAllByChatId (Long id);
}
