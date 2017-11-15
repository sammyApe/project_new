package com.sammy.project.schedule.service;

import com.sammy.project.schedule.domain.Time;

import java.util.List;

/**
 * Service Interface for managing Time.
 */
public interface TimeService {

    /**
     * Save a time.
     *
     * @param time the entity to save
     * @return the persisted entity
     */
    Time save(Time time);

    /**
     *  Get all the times.
     *  
     *  @return the list of entities
     */
    List<Time> findAll();

    /**
     *  Get the "id" time.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Time findOne(Long id);

    /**
     *  Delete the "id" time.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
