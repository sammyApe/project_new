package com.sammy.project.schedule.service;

import com.sammy.project.schedule.domain.Schedule;

import java.util.List;

/**
 * Service Interface for managing Schedule.
 */
public interface ScheduleService {

    /**
     * Save a schedule.
     *
     * @param schedule the entity to save
     * @return the persisted entity
     */
    Schedule save(Schedule schedule);

    /**
     *  Get all the schedules.
     *  
     *  @return the list of entities
     */
    List<Schedule> findAll();

    /**
     *  Get the "id" schedule.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Schedule findOne(Long id);

    /**
     *  Delete the "id" schedule.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
