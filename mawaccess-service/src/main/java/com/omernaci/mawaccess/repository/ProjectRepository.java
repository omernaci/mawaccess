package com.omernaci.mawaccess.repository;

import com.omernaci.mawaccess.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsProjectByUrl(String url);

    List<Project> findAllByOrderByIdDesc();

}
