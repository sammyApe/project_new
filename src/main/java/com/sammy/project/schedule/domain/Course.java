package com.sammy.project.schedule.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.sammy.project.schedule.domain.enumeration.SemesterEnum;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester")
    private SemesterEnum semester;

    @Column(name = "number_Of_Sections")
    private Integer numberOfSections;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "course_section_list",
               joinColumns = @JoinColumn(name="courses_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="section_lists_id", referencedColumnName="ID"))
    private Set<Session> sectionLists = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Course name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Course code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public Course semester(SemesterEnum semester) {
        this.semester = semester;
        return this;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    public Set<Session> getSectionLists() {
        return sectionLists;
    }

    public Course sectionLists(Set<Session> sessions) {
        this.sectionLists = sessions;
        return this;
    }

    public Course addSectionList(Session session) {
        sectionLists.add(session);
        return this;
    }

    public Course removeSectionList(Session session) {
        sectionLists.remove(session);
        return this;
    }

    public void setSectionLists(Set<Session> sessions) {
        this.sectionLists = sessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        if(course.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", code='" + code + "'" +
            ", semester='" + semester + "'" +
            '}';
    }
}
