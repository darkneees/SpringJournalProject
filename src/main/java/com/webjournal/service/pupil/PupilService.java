package com.webjournal.service.pupil;

import com.webjournal.entity.Pupil;

import java.util.List;

public interface PupilService {

    void addPupil(Pupil pupil);
    List<Pupil> findPupilsByClassP(String classP);
    void addMarkPupil(Long id, String subject, String date, String mark);

    List<Pupil> getAllPupils();

    void deletePupilById(Long id);

    Pupil findPupilById(Long id);

    void editPupilById(Long id, String firstName, String lastName, String classP);

    void deleteMarkByDate(Long id, String selectedSubject, String date);
}
