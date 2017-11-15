package com.sammy.project.schedule.web.rest;

import com.sammy.project.schedule.SchedulingProjectApp;

import com.sammy.project.schedule.domain.Lecturer;
import com.sammy.project.schedule.repository.LecturerRepository;
import com.sammy.project.schedule.service.LecturerService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sammy.project.schedule.domain.enumeration.SemesterEnum;
/**
 * Test class for the LecturerResource REST controller.
 *
 * @see LecturerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchedulingProjectApp.class)
public class LecturerResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TEACHING_LOAD = 1;
    private static final Integer UPDATED_TEACHING_LOAD = 2;

    private static final SemesterEnum DEFAULT_SEMESTER = SemesterEnum.SPRING;
    private static final SemesterEnum UPDATED_SEMESTER = SemesterEnum.SUMMER;

    @Inject
    private LecturerRepository lecturerRepository;

    @Inject
    private LecturerService lecturerService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restLecturerMockMvc;

    private Lecturer lecturer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LecturerResource lecturerResource = new LecturerResource();
        ReflectionTestUtils.setField(lecturerResource, "lecturerService", lecturerService);
        this.restLecturerMockMvc = MockMvcBuilders.standaloneSetup(lecturerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lecturer createEntity(EntityManager em) {
        Lecturer lecturer = new Lecturer()
                .name(DEFAULT_NAME)
                .teachingLoad(DEFAULT_TEACHING_LOAD)
                .semester(DEFAULT_SEMESTER);
        return lecturer;
    }

    @Before
    public void initTest() {
        lecturer = createEntity(em);
    }

    @Test
    @Transactional
    public void createLecturer() throws Exception {
        int databaseSizeBeforeCreate = lecturerRepository.findAll().size();

        // Create the Lecturer

        restLecturerMockMvc.perform(post("/api/lecturers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(lecturer)))
                .andExpect(status().isCreated());

        // Validate the Lecturer in the database
        List<Lecturer> lecturers = lecturerRepository.findAll();
        assertThat(lecturers).hasSize(databaseSizeBeforeCreate + 1);
        Lecturer testLecturer = lecturers.get(lecturers.size() - 1);
        assertThat(testLecturer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLecturer.getTeachingLoad()).isEqualTo(DEFAULT_TEACHING_LOAD);
        assertThat(testLecturer.getSemester()).isEqualTo(DEFAULT_SEMESTER);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = lecturerRepository.findAll().size();
        // set the field null
        lecturer.setName(null);

        // Create the Lecturer, which fails.

        restLecturerMockMvc.perform(post("/api/lecturers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(lecturer)))
                .andExpect(status().isBadRequest());

        List<Lecturer> lecturers = lecturerRepository.findAll();
        assertThat(lecturers).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLecturers() throws Exception {
        // Initialize the database
        lecturerRepository.saveAndFlush(lecturer);

        // Get all the lecturers
        restLecturerMockMvc.perform(get("/api/lecturers?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(lecturer.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].teachingLoad").value(hasItem(DEFAULT_TEACHING_LOAD)))
                .andExpect(jsonPath("$.[*].semester").value(hasItem(DEFAULT_SEMESTER.toString())));
    }

    @Test
    @Transactional
    public void getLecturer() throws Exception {
        // Initialize the database
        lecturerRepository.saveAndFlush(lecturer);

        // Get the lecturer
        restLecturerMockMvc.perform(get("/api/lecturers/{id}", lecturer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lecturer.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.teachingLoad").value(DEFAULT_TEACHING_LOAD))
            .andExpect(jsonPath("$.semester").value(DEFAULT_SEMESTER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLecturer() throws Exception {
        // Get the lecturer
        restLecturerMockMvc.perform(get("/api/lecturers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLecturer() throws Exception {
        // Initialize the database
        lecturerService.save(lecturer);

        int databaseSizeBeforeUpdate = lecturerRepository.findAll().size();

        // Update the lecturer
        Lecturer updatedLecturer = lecturerRepository.findOne(lecturer.getId());
        updatedLecturer
                .name(UPDATED_NAME)
                .teachingLoad(UPDATED_TEACHING_LOAD)
                .semester(UPDATED_SEMESTER);

        restLecturerMockMvc.perform(put("/api/lecturers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedLecturer)))
                .andExpect(status().isOk());

        // Validate the Lecturer in the database
        List<Lecturer> lecturers = lecturerRepository.findAll();
        assertThat(lecturers).hasSize(databaseSizeBeforeUpdate);
        Lecturer testLecturer = lecturers.get(lecturers.size() - 1);
        assertThat(testLecturer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLecturer.getTeachingLoad()).isEqualTo(UPDATED_TEACHING_LOAD);
        assertThat(testLecturer.getSemester()).isEqualTo(UPDATED_SEMESTER);
    }

    @Test
    @Transactional
    public void deleteLecturer() throws Exception {
        // Initialize the database
        lecturerService.save(lecturer);

        int databaseSizeBeforeDelete = lecturerRepository.findAll().size();

        // Get the lecturer
        restLecturerMockMvc.perform(delete("/api/lecturers/{id}", lecturer.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Lecturer> lecturers = lecturerRepository.findAll();
        assertThat(lecturers).hasSize(databaseSizeBeforeDelete - 1);
    }
}
