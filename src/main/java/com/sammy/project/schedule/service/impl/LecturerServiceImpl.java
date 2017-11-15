package com.sammy.project.schedule.service.impl;

import com.sammy.project.schedule.service.LecturerService;
import com.sammy.project.schedule.domain.Lecturer;
import com.sammy.project.schedule.repository.LecturerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Lecturer.
 */
@Service
@Transactional
public class LecturerServiceImpl implements LecturerService{

    private final Logger log = LoggerFactory.getLogger(LecturerServiceImpl.class);
    
    @Inject
    private LecturerRepository lecturerRepository;

    /**
     * Save a lecturer.
     *
     * @param lecturer the entity to save
     * @return the persisted entity
     */
    public Lecturer save(Lecturer lecturer) {
        log.debug("Request to save Lecturer : {}", lecturer);
        Lecturer result = lecturerRepository.save(lecturer);
        return result;
    }

    /**
     *  Get all the lecturers.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Lecturer> findAll() {
        log.debug("Request to get all Lecturers");
        List<Lecturer> result = lecturerRepository.findAllWithEagerRelationships();

        return result;
    }

    /**
     *  Get one lecturer by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Lecturer findOne(Long id) {
        log.debug("Request to get Lecturer : {}", id);
        Lecturer lecturer = lecturerRepository.findOneWithEagerRelationships(id);
        return lecturer;
    }

    /**
     *  Delete the  lecturer by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Lecturer : {}", id);
        lecturerRepository.delete(id);
    }
}
