package com.sammy.project.schedule.service;

import com.sammy.project.schedule.domain.Session;

import java.util.List;

/**
 * Service Interface for managing Session.
 */
public interface SessionService {

    /**
     * Save a session.
     *
     * @param session the entity to save
     * @return the persisted entity
     */
    Session save(Session session);

    /**
     *  Get all the sessions.
     *  
     *  @return the list of entities
     */
    List<Session> findAll();

    /**
     *  Get the "id" session.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Session findOne(Long id);

    /**
     *  Delete the "id" session.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
