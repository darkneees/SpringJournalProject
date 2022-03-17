package com.webjournal.Repository;

import com.webjournal.Entity.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PupilRepository extends JpaRepository<Pupil, Integer> {

    List<Pupil> findAllByStudyClass(String studyClass);
}
