package com.sammy.project.schedule.service.impl;

import com.sammy.project.schedule.service.SessionService;
import com.sammy.project.schedule.domain.Session;
import com.sammy.project.schedule.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Session.
 */
@Service
@Transactional
public class SessionServiceImpl implements SessionService{

    private final Logger log = LoggerFactory.getLogger(SessionServiceImpl.class);
    
    @Inject
    private SessionRepository sessionRepository;

    /**
     * Save a session.
     *
     * @param session the entity to save
     * @return the persisted entity
     */
    public Session save(Session session) {
        log.debug("Request to save Session : {}", session);
        Session result = sessionRepository.save(session);
        return result;
    }

    /**
     *  Get all the sessions.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Session> findAll() {
        log.debug("Request to get all Sessions");
        List<Session> result = sessionRepository.findAll();

        return result;
    }

    /**
     *  Get one session by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Session findOne(Long id) {
        log.debug("Request to get Session : {}", id);
        Session session = sessionRepository.findOne(id);
        return session;
    }

    /**
     *  Delete the  session by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Session : {}", id);
        sessionRepository.delete(id);
    }
}
