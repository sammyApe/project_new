package com.sammy.project.schedule.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.sammy.project.schedule.domain.enumeration.Day;

/**
 * A DayTime.
 */
@Entity
@Table(name = "day_time")
public class DayTime implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_preference")
    private Day dayPreference;

    @ManyToOne
    private Lecturer lecturer;

    @OneToMany(mappedBy = "dayTime")
    @JsonIgnore
    private Set<Time> timePreferenceLists = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Day getDayPreference() {
        return dayPreference;
    }

    public DayTime dayPreference(Day dayPreference) {
        this.dayPreference = dayPreference;
        return this;
    }

    public void setDayPreference(Day dayPreference) {
        this.dayPreference = dayPreference;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public DayTime lecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
        return this;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Set<Time> getTimePreferenceLists() {
        return timePreferenceLists;
    }

    public DayTime timePreferenceLists(Set<Time> times) {
        this.timePreferenceLists = times;
        return this;
    }

    public DayTime addTimePreferenceList(Time time) {
        timePreferenceLists.add(time);
        time.setDayTime(this);
        return this;
    }

    public DayTime removeTimePreferenceList(Time time) {
        timePreferenceLists.remove(time);
        time.setDayTime(null);
        return this;
    }

    public void setTimePreferenceLists(Set<Time> times) {
        this.timePreferenceLists = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DayTime dayTime = (DayTime) o;
        if(dayTime.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, dayTime.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DayTime{" +
            "id=" + id +
            ", dayPreference='" + dayPreference + "'" +
            '}';
    }
}
