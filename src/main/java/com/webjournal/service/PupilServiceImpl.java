package com.webjournal.service;

import com.webjournal.entity.Pupil;
import com.webjournal.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PupilServiceImpl implements PupilService {

    @Autowired
    PupilRepository pupilRepository;

    public void addPupil(Pupil pupil){
        pupilRepository.save(pupil);
    }

    @Override
    public List<Pupil> findPupilsByClassP(String classP) {
        return pupilRepository.findAllByClassP(classP);
    }

    @Override
    public void addMarkPupil(Long id, String subject, String date, String mark) {
        Pupil pupil = pupilRepository.findById(id).get();

        pupil.addMarkInData(subject, date, mark);

        pupilRepository.save(pupil);
    }
}
