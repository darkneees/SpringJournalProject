package com.webjournal.Service;

import com.webjournal.Entity.Pupil;
import com.webjournal.Repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PupilServiceImpl implements PupilService {

    @Autowired
    PupilRepository pupilRepository;

    @Override
    public List<Pupil> getAllPupilsByClass(String studyClass) {
        return pupilRepository.findAllByStudyClass(studyClass);
    }
}
