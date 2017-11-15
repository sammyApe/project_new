package com.sammy.project.schedule.service.impl;

import com.sammy.project.schedule.service.TimeService;
import com.sammy.project.schedule.domain.Time;
import com.sammy.project.schedule.repository.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Time.
 */
@Service
@Transactional
public class TimeServiceImpl implements TimeService{

    private final Logger log = LoggerFactory.getLogger(TimeServiceImpl.class);
    
    @Inject
    private TimeRepository timeRepository;

    /**
     * Save a time.
     *
     * @param time the entity to save
     * @return the persisted entity
     */
    public Time save(Time time) {
        log.debug("Request to save Time : {}", time);
        Time result = timeRepository.save(time);
        return result;
    }

    /**
     *  Get all the times.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Time> findAll() {
        log.debug("Request to get all Times");
        List<Time> result = timeRepository.findAll();

        return result;
    }

    /**
     *  Get one time by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Time findOne(Long id) {
        log.debug("Request to get Time : {}", id);
        Time time = timeRepository.findOne(id);
        return time;
    }

    /**
     *  Delete the  time by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Time : {}", id);
        timeRepository.delete(id);
    }
}
