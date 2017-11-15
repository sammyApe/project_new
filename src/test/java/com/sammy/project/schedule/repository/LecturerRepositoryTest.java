package com.sammy.project.schedule.repository;

import com.google.common.collect.Sets;
import com.sammy.project.schedule.SchedulingProjectApp;
import com.sammy.project.schedule.domain.Course;
import com.sammy.project.schedule.domain.DayTime;
import com.sammy.project.schedule.domain.Lecturer;
import com.sammy.project.schedule.domain.Time;
import com.sammy.project.schedule.domain.enumeration.Day;
import com.sammy.project.schedule.domain.enumeration.TimePreference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by samuel on 12/4/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchedulingProjectApp.class)
public class LecturerRepositoryTest {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void lecturerRepositoryTest() {
        Course course = new Course();
        course.setName("course1");
        course.setCode("code1");
        course.setId(2L);
        courseRepository.save(course);

        Set<Course> courseList = Sets.newHashSet();
        courseList.add(course);

        Time time = new Time();
        time.setTimePreference(TimePreference.AFTERNOONS_AND_EVENINGS);
        Set<Time> timeList = Sets.newHashSet();
        timeList.add(time);

        DayTime dayTime = new DayTime();
        dayTime.dayPreference(Day.FRIDAY);

        dayTime.setId((long) 9);

        Set<DayTime> dayTimeSet = new HashSet<>();
        dayTimeSet.add(dayTime);


        Lecturer lecturer = new Lecturer();
        lecturer.setId((long) 1);
        lecturer.setCourseLists(courseList);


        lecturer.setName("Name");




    }

}
