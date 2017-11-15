package com.sammy.project.schedule.web.rest;

import com.sammy.project.schedule.SchedulingProjectApp;

import com.sammy.project.schedule.domain.Time;
import com.sammy.project.schedule.repository.TimeRepository;
import com.sammy.project.schedule.service.TimeService;

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

import com.sammy.project.schedule.domain.enumeration.TimePreference;
/**
 * Test class for the TimeResource REST controller.
 *
 * @see TimeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchedulingProjectApp.class)
public class TimeResourceIntTest {

    private static final TimePreference DEFAULT_TIME_PREFERENCE = TimePreference.MORNINGS;
    private static final TimePreference UPDATED_TIME_PREFERENCE = TimePreference.MORNINGS_AND_AFTERNOONS;

    @Inject
    private TimeRepository timeRepository;

    @Inject
    private TimeService timeService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restTimeMockMvc;

    private Time time;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TimeResource timeResource = new TimeResource();
        ReflectionTestUtils.setField(timeResource, "timeService", timeService);
        this.restTimeMockMvc = MockMvcBuilders.standaloneSetup(timeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Time createEntity(EntityManager em) {
        Time time = new Time()
                .timePreference(DEFAULT_TIME_PREFERENCE);
        return time;
    }

    @Before
    public void initTest() {
        time = createEntity(em);
    }

    @Test
    @Transactional
    public void createTime() throws Exception {
        int databaseSizeBeforeCreate = timeRepository.findAll().size();

        // Create the Time

        restTimeMockMvc.perform(post("/api/times")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(time)))
                .andExpect(status().isCreated());

        // Validate the Time in the database
        List<Time> times = timeRepository.findAll();
        assertThat(times).hasSize(databaseSizeBeforeCreate + 1);
        Time testTime = times.get(times.size() - 1);
        assertThat(testTime.getTimePreference()).isEqualTo(DEFAULT_TIME_PREFERENCE);
    }

    @Test
    @Transactional
    public void getAllTimes() throws Exception {
        // Initialize the database
        timeRepository.saveAndFlush(time);

        // Get all the times
        restTimeMockMvc.perform(get("/api/times?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(time.getId().intValue())))
                .andExpect(jsonPath("$.[*].timePreference").value(hasItem(DEFAULT_TIME_PREFERENCE.toString())));
    }

    @Test
    @Transactional
    public void getTime() throws Exception {
        // Initialize the database
        timeRepository.saveAndFlush(time);

        // Get the time
        restTimeMockMvc.perform(get("/api/times/{id}", time.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(time.getId().intValue()))
            .andExpect(jsonPath("$.timePreference").value(DEFAULT_TIME_PREFERENCE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTime() throws Exception {
        // Get the time
        restTimeMockMvc.perform(get("/api/times/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTime() throws Exception {
        // Initialize the database
        timeService.save(time);

        int databaseSizeBeforeUpdate = timeRepository.findAll().size();

        // Update the time
        Time updatedTime = timeRepository.findOne(time.getId());
        updatedTime
                .timePreference(UPDATED_TIME_PREFERENCE);

        restTimeMockMvc.perform(put("/api/times")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedTime)))
                .andExpect(status().isOk());

        // Validate the Time in the database
        List<Time> times = timeRepository.findAll();
        assertThat(times).hasSize(databaseSizeBeforeUpdate);
        Time testTime = times.get(times.size() - 1);
        assertThat(testTime.getTimePreference()).isEqualTo(UPDATED_TIME_PREFERENCE);
    }

    @Test
    @Transactional
    public void deleteTime() throws Exception {
        // Initialize the database
        timeService.save(time);

        int databaseSizeBeforeDelete = timeRepository.findAll().size();

        // Get the time
        restTimeMockMvc.perform(delete("/api/times/{id}", time.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Time> times = timeRepository.findAll();
        assertThat(times).hasSize(databaseSizeBeforeDelete - 1);
    }
}
