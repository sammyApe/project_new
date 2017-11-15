package com.sammy.project.schedule.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.sammy.project.schedule.domain.DayTime;

import com.sammy.project.schedule.repository.DayTimeRepository;
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
 * REST controller for managing DayTime.
 */
@RestController
@RequestMapping("/api")
public class DayTimeResource {

    private final Logger log = LoggerFactory.getLogger(DayTimeResource.class);
        
    @Inject
    private DayTimeRepository dayTimeRepository;

    /**
     * POST  /day-times : Create a new dayTime.
     *
     * @param dayTime the dayTime to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dayTime, or with status 400 (Bad Request) if the dayTime has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/day-times")
    @Timed
    public ResponseEntity<DayTime> createDayTime(@RequestBody DayTime dayTime) throws URISyntaxException {
        log.debug("REST request to save DayTime : {}", dayTime);
        if (dayTime.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("dayTime", "idexists", "A new dayTime cannot already have an ID")).body(null);
        }
        DayTime result = dayTimeRepository.save(dayTime);
        return ResponseEntity.created(new URI("/api/day-times/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("dayTime", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /day-times : Updates an existing dayTime.
     *
     * @param dayTime the dayTime to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dayTime,
     * or with status 400 (Bad Request) if the dayTime is not valid,
     * or with status 500 (Internal Server Error) if the dayTime couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/day-times")
    @Timed
    public ResponseEntity<DayTime> updateDayTime(@RequestBody DayTime dayTime) throws URISyntaxException {
        log.debug("REST request to update DayTime : {}", dayTime);
        if (dayTime.getId() == null) {
            return createDayTime(dayTime);
        }
        DayTime result = dayTimeRepository.save(dayTime);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("dayTime", dayTime.getId().toString()))
            .body(result);
    }

    /**
     * GET  /day-times : get all the dayTimes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of dayTimes in body
     */
    @GetMapping("/day-times")
    @Timed
    public List<DayTime> getAllDayTimes() {
        log.debug("REST request to get all DayTimes");
        List<DayTime> dayTimes = dayTimeRepository.findAll();
        return dayTimes;
    }

    /**
     * GET  /day-times/:id : get the "id" dayTime.
     *
     * @param id the id of the dayTime to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dayTime, or with status 404 (Not Found)
     */
    @GetMapping("/day-times/{id}")
    @Timed
    public ResponseEntity<DayTime> getDayTime(@PathVariable Long id) {
        log.debug("REST request to get DayTime : {}", id);
        DayTime dayTime = dayTimeRepository.findOne(id);
        return Optional.ofNullable(dayTime)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /day-times/:id : delete the "id" dayTime.
     *
     * @param id the id of the dayTime to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/day-times/{id}")
    @Timed
    public ResponseEntity<Void> deleteDayTime(@PathVariable Long id) {
        log.debug("REST request to delete DayTime : {}", id);
        dayTimeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("dayTime", id.toString())).build();
    }

}
