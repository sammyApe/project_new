package com.sammy.project.schedule.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.sammy.project.schedule.domain.Session;
import com.sammy.project.schedule.service.SessionService;
import com.sammy.project.schedule.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Session.
 */
@RestController
@RequestMapping("/api")
public class SessionResource {

    private final Logger log = LoggerFactory.getLogger(SessionResource.class);
        
    @Inject
    private SessionService sessionService;

    /**
     * POST  /sessions : Create a new session.
     *
     * @param session the session to create
     * @return the ResponseEntity with status 201 (Created) and with body the new session, or with status 400 (Bad Request) if the session has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sessions")
    @Timed
    public ResponseEntity<Session> createSession(@RequestBody Session session) throws URISyntaxException {
        log.debug("REST request to save Session : {}", session);
        if (session.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("session", "idexists", "A new session cannot already have an ID")).body(null);
        }
        Session result = sessionService.save(session);
        return ResponseEntity.created(new URI("/api/sessions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("session", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sessions : Updates an existing session.
     *
     * @param session the session to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated session,
     * or with status 400 (Bad Request) if the session is not valid,
     * or with status 500 (Internal Server Error) if the session couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sessions")
    @Timed
    public ResponseEntity<Session> updateSession(@RequestBody Session session) throws URISyntaxException {
        log.debug("REST request to update Session : {}", session);
        if (session.getId() == null) {
            return createSession(session);
        }
        Session result = sessionService.save(session);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("session", session.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sessions : get all the sessions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sessions in body
     */
    @GetMapping("/sessions")
    @Timed
    public List<Session> getAllSessions() {
        log.debug("REST request to get all Sessions");
        return sessionService.findAll();
    }

    /**
     * GET  /sessions/:id : get the "id" session.
     *
     * @param id the id of the session to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the session, or with status 404 (Not Found)
     */
    @GetMapping("/sessions/{id}")
    @Timed
    public ResponseEntity<Session> getSession(@PathVariable Long id) {
        log.debug("REST request to get Session : {}", id);
        Session session = sessionService.findOne(id);
        return Optional.ofNullable(session)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /sessions/:id : delete the "id" session.
     *
     * @param id the id of the session to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sessions/{id}")
    @Timed
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        log.debug("REST request to delete Session : {}", id);
        sessionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("session", id.toString())).build();
    }

}
