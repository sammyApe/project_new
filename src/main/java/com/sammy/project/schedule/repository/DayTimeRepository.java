package com.sammy.project.schedule.repository;

import com.sammy.project.schedule.domain.DayTime;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DayTime entity.
 */
@SuppressWarnings("unused")
public interface DayTimeRepository extends JpaRepository<DayTime,Long> {

}
