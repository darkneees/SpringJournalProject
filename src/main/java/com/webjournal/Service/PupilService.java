package com.webjournal.Service;

import com.webjournal.Entity.Pupil;

import java.util.List;

public interface PupilService {

    List<Pupil> getAllPupilsByClass(String studyClass);
}
