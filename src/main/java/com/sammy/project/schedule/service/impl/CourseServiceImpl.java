package com.sammy.project.schedule.service.impl;

import com.sammy.project.schedule.service.CourseService;
import com.sammy.project.schedule.domain.Course;
import com.sammy.project.schedule.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Course.
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    private final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
    
    @Inject
    private CourseRepository courseRepository;

    /**
     * Save a course.
     *
     * @param course the entity to save
     * @return the persisted entity
     */
    public Course save(Course course) {
        log.debug("Request to save Course : {}", course);
        Course result = courseRepository.save(course);
        return result;
    }

    /**
     *  Get all the courses.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Course> findAll() {
        log.debug("Request to get all Courses");
        List<Course> result = courseRepository.findAllWithEagerRelationships();

        return result;
    }

    /**
     *  Get one course by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Course findOne(Long id) {
        log.debug("Request to get Course : {}", id);
        Course course = courseRepository.findOneWithEagerRelationships(id);
        return course;
    }

    /**
     *  Delete the  course by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Course : {}", id);
        courseRepository.delete(id);
    }
}
