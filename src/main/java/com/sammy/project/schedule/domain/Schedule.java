package com.sammy.project.schedule.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.sammy.project.schedule.domain.enumeration.SemesterEnum;

/**
 * A Schedule.
 */
@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester")
    private SemesterEnum semester;

    @ManyToMany
    @JoinTable(name = "schedule_lecturer_list",
               joinColumns = @JoinColumn(name="schedules_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="lecturer_lists_id", referencedColumnName="ID"))
    private Set<Lecturer> lecturerLists = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "schedule_course_list",
               joinColumns = @JoinColumn(name="schedules_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="course_lists_id", referencedColumnName="ID"))
    private Set<Course> courseLists = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Schedule date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Schedule name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public Schedule semester(SemesterEnum semester) {
        this.semester = semester;
        return this;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    public Set<Lecturer> getLecturerLists() {
        return lecturerLists;
    }

    public Schedule lecturerLists(Set<Lecturer> lecturers) {
        this.lecturerLists = lecturers;
        return this;
    }

    public Schedule addLecturerList(Lecturer lecturer) {
        lecturerLists.add(lecturer);
        return this;
    }

    public Schedule removeLecturerList(Lecturer lecturer) {
        lecturerLists.remove(lecturer);
        return this;
    }

    public void setLecturerLists(Set<Lecturer> lecturers) {
        this.lecturerLists = lecturers;
    }

    public Set<Course> getCourseLists() {
        return courseLists;
    }

    public Schedule courseLists(Set<Course> courses) {
        this.courseLists = courses;
        return this;
    }

    public Schedule addCourseList(Course course) {
        courseLists.add(course);
        return this;
    }

    public Schedule removeCourseList(Course course) {
        courseLists.remove(course);
        return this;
    }

    public void setCourseLists(Set<Course> courses) {
        this.courseLists = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Schedule schedule = (Schedule) o;
        if(schedule.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, schedule.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Schedule{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", name='" + name + "'" +
            ", semester='" + semester + "'" +
            '}';
    }
}
