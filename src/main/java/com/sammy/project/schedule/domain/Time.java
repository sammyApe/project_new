package com.sammy.project.schedule.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import com.sammy.project.schedule.domain.enumeration.TimePreference;

/**
 * A Time.
 */
@Entity
@Table(name = "time")
public class Time implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_preference")
    private TimePreference timePreference;

    @ManyToOne
    private DayTime dayTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimePreference getTimePreference() {
        return timePreference;
    }

    public Time timePreference(TimePreference timePreference) {
        this.timePreference = timePreference;
        return this;
    }

    public void setTimePreference(TimePreference timePreference) {
        this.timePreference = timePreference;
    }

    public DayTime getDayTime() {
        return dayTime;
    }

    public Time dayTime(DayTime dayTime) {
        this.dayTime = dayTime;
        return this;
    }

    public void setDayTime(DayTime dayTime) {
        this.dayTime = dayTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Time time = (Time) o;
        if(time.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, time.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Time{" +
            "id=" + id +
            ", timePreference='" + timePreference + "'" +
            '}';
    }
}
