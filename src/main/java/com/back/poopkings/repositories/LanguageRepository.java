package com.back.poopkings.repositories;

import com.back.poopkings.models.database.LanguageMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageMO, Long> {
}
