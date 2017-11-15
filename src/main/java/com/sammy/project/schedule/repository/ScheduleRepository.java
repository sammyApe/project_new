package com.sammy.project.schedule.repository;

import com.sammy.project.schedule.domain.Schedule;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Schedule entity.
 */
@SuppressWarnings("unused")
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    @Query("select distinct schedule from Schedule schedule left join fetch schedule.lecturerLists left join fetch schedule.courseLists")
    List<Schedule> findAllWithEagerRelationships();

    @Query("select schedule from Schedule schedule left join fetch schedule.lecturerLists left join fetch schedule.courseLists where schedule.id =:id")
    Schedule findOneWithEagerRelationships(@Param("id") Long id);

}
