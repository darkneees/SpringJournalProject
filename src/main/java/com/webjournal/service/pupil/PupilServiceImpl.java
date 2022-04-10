package com.webjournal.service.pupil;

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

    @Override
    public List<Pupil> getAllPupils() {
        return pupilRepository.findAll();
    }

    @Override
    public void deletePupilById(Long id) {
        pupilRepository.deleteById(id);
    }

    @Override
    public Pupil findPupilById(Long id) {
        return pupilRepository.findById(id).get();
    }

    @Override
    public void editPupilById(Long id, String firstName, String lastName, String classP) {
        Pupil pupil = pupilRepository.findById(id).get();
        pupil.setFirstName(firstName);
        pupil.setLastName(lastName);
        pupil.setClassP(classP);

        pupilRepository.save(pupil);

    }

    @Override
    public void deleteMarkByDate(Long id, String selectedSubject, String date) {
        Pupil pupil = pupilRepository.findById(id).get();
        pupil.deleteMark(selectedSubject, date);
        pupilRepository.save(pupil);

    }
}