package com.omernaci.mawaccess.repository;

import com.omernaci.mawaccess.domain.ACheckerSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ACheckerRepository extends JpaRepository<ACheckerSummary, Long> {
}
