package com.webjournal.repository;

import com.webjournal.entity.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PupilRepository extends JpaRepository<Pupil, Long> {

    List<Pupil> findAllByClassP(String classP);
    Pupil findByFirstNameAndLastNameAndClassP(String firstName, String lastName, String classP);
}
