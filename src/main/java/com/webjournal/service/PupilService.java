package com.webjournal.service;

import com.webjournal.entity.Pupil;

import java.util.List;

public interface PupilService {

    void addPupil(Pupil pupil);
    List<Pupil> findPupilsByClassP(String classP);
}
