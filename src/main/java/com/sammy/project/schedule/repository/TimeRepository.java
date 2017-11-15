package com.sammy.project.schedule.repository;

import com.sammy.project.schedule.domain.Time;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Time entity.
 */
@SuppressWarnings("unused")
public interface TimeRepository extends JpaRepository<Time,Long> {

}
