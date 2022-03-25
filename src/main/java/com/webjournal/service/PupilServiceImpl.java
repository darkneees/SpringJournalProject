package com.webjournal.service;

import com.webjournal.entity.Pupil;
import com.webjournal.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PupilServiceImpl {

    @Autowired
    PupilRepository pupilRepository;

    public void addPupil(Pupil pupil){
        pupilRepository.save(pupil);
    }
}
