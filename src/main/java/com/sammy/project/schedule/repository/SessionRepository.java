package com.sammy.project.schedule.repository;

import com.sammy.project.schedule.domain.Session;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Session entity.
 */
@SuppressWarnings("unused")
public interface SessionRepository extends JpaRepository<Session,Long> {

}
