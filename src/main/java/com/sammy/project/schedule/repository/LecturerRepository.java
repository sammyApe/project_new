package com.sammy.project.schedule.repository;

import com.sammy.project.schedule.domain.Lecturer;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Lecturer entity.
 */
@SuppressWarnings("unused")
public interface LecturerRepository extends JpaRepository<Lecturer,Long> {

    @Query("select distinct lecturer from Lecturer lecturer left join fetch lecturer.courseLists left join fetch lecturer.sessionLists")
    List<Lecturer> findAllWithEagerRelationships();

    @Query("select lecturer from Lecturer lecturer left join fetch lecturer.courseLists left join fetch lecturer.sessionLists where lecturer.id =:id")
    Lecturer findOneWithEagerRelationships(@Param("id") Long id);

}
