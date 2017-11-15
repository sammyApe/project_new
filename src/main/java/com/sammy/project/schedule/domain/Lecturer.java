package com.sammy.project.schedule.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.sammy.project.schedule.domain.enumeration.SemesterEnum;

/**
 * A Lecturer.
 */
@Entity
@Table(name = "lecturer")
public class Lecturer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "teaching_load")
    private Integer teachingLoad;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester")
    private SemesterEnum semester;

    @OneToMany(mappedBy = "lecturer")
    @JsonIgnore
    private Set<DayTime> preferredDayTimeLists = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "lecturer_course_list",
               joinColumns = @JoinColumn(name="lecturers_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="course_lists_id", referencedColumnName="ID"))
    private Set<Course> courseLists = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "lecturer_session_list",
               joinColumns = @JoinColumn(name="lecturers_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="session_lists_id", referencedColumnName="ID"))
    private Set<Session> sessionLists = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Lecturer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeachingLoad() {
        return teachingLoad;
    }

    public Lecturer teachingLoad(Integer teachingLoad) {
        this.teachingLoad = teachingLoad;
        return this;
    }

    public void setTeachingLoad(Integer teachingLoad) {
        this.teachingLoad = teachingLoad;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public Lecturer semester(SemesterEnum semester) {
        this.semester = semester;
        return this;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    public Set<DayTime> getPreferredDayTimeLists() {
        return preferredDayTimeLists;
    }

    public Lecturer preferredDayTimeLists(Set<DayTime> dayTimes) {
        this.preferredDayTimeLists = dayTimes;
        return this;
    }

    public Lecturer addPreferredDayTimeList(DayTime dayTime) {
        preferredDayTimeLists.add(dayTime);
        dayTime.setLecturer(this);
        return this;
    }

    public Lecturer removePreferredDayTimeList(DayTime dayTime) {
        preferredDayTimeLists.remove(dayTime);
        dayTime.setLecturer(null);
        return this;
    }

    public void setPreferredDayTimeLists(Set<DayTime> dayTimes) {
        this.preferredDayTimeLists = dayTimes;
    }

    public Set<Course> getCourseLists() {
        return courseLists;
    }

    public Lecturer courseLists(Set<Course> courses) {
        this.courseLists = courses;
        return this;
    }

    public Lecturer addCourseList(Course course) {
        courseLists.add(course);
        return this;
    }

    public Lecturer removeCourseList(Course course) {
        courseLists.remove(course);
        return this;
    }

    public void setCourseLists(Set<Course> courses) {
        this.courseLists = courses;
    }

    public Set<Session> getSessionLists() {
        return sessionLists;
    }

    public Lecturer sessionLists(Set<Session> sessions) {
        this.sessionLists = sessions;
        return this;
    }

    public Lecturer addSessionList(Session session) {
        sessionLists.add(session);
        return this;
    }

    public Lecturer removeSessionList(Session session) {
        sessionLists.remove(session);
        return this;
    }

    public void setSessionLists(Set<Session> sessions) {
        this.sessionLists = sessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lecturer lecturer = (Lecturer) o;
        if(lecturer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, lecturer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Lecturer{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", teachingLoad='" + teachingLoad + "'" +
            ", semester='" + semester + "'" +
            '}';
    }
}
