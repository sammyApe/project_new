package com.sammy.project.schedule.web.rest;

import com.sammy.project.schedule.SchedulingProjectApp;

import com.sammy.project.schedule.domain.DayTime;
import com.sammy.project.schedule.repository.DayTimeRepository;

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

import com.sammy.project.schedule.domain.enumeration.Day;
/**
 * Test class for the DayTimeResource REST controller.
 *
 * @see DayTimeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchedulingProjectApp.class)
public class DayTimeResourceIntTest {

    private static final Day DEFAULT_DAY_PREFERENCE = Day.MONDAY;
    private static final Day UPDATED_DAY_PREFERENCE = Day.TUESDAY;

    @Inject
    private DayTimeRepository dayTimeRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restDayTimeMockMvc;

    private DayTime dayTime;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DayTimeResource dayTimeResource = new DayTimeResource();
        ReflectionTestUtils.setField(dayTimeResource, "dayTimeRepository", dayTimeRepository);
        this.restDayTimeMockMvc = MockMvcBuilders.standaloneSetup(dayTimeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DayTime createEntity(EntityManager em) {
        DayTime dayTime = new DayTime()
                .dayPreference(DEFAULT_DAY_PREFERENCE);
        return dayTime;
    }

    @Before
    public void initTest() {
        dayTime = createEntity(em);
    }

    @Test
    @Transactional
    public void createDayTime() throws Exception {
        int databaseSizeBeforeCreate = dayTimeRepository.findAll().size();

        // Create the DayTime

        restDayTimeMockMvc.perform(post("/api/day-times")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dayTime)))
                .andExpect(status().isCreated());

        // Validate the DayTime in the database
        List<DayTime> dayTimes = dayTimeRepository.findAll();
        assertThat(dayTimes).hasSize(databaseSizeBeforeCreate + 1);
        DayTime testDayTime = dayTimes.get(dayTimes.size() - 1);
        assertThat(testDayTime.getDayPreference()).isEqualTo(DEFAULT_DAY_PREFERENCE);
    }

    @Test
    @Transactional
    public void getAllDayTimes() throws Exception {
        // Initialize the database
        dayTimeRepository.saveAndFlush(dayTime);

        // Get all the dayTimes
        restDayTimeMockMvc.perform(get("/api/day-times?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(dayTime.getId().intValue())))
                .andExpect(jsonPath("$.[*].dayPreference").value(hasItem(DEFAULT_DAY_PREFERENCE.toString())));
    }

    @Test
    @Transactional
    public void getDayTime() throws Exception {
        // Initialize the database
        dayTimeRepository.saveAndFlush(dayTime);

        // Get the dayTime
        restDayTimeMockMvc.perform(get("/api/day-times/{id}", dayTime.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dayTime.getId().intValue()))
            .andExpect(jsonPath("$.dayPreference").value(DEFAULT_DAY_PREFERENCE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDayTime() throws Exception {
        // Get the dayTime
        restDayTimeMockMvc.perform(get("/api/day-times/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDayTime() throws Exception {
        // Initialize the database
        dayTimeRepository.saveAndFlush(dayTime);
        int databaseSizeBeforeUpdate = dayTimeRepository.findAll().size();

        // Update the dayTime
        DayTime updatedDayTime = dayTimeRepository.findOne(dayTime.getId());
        updatedDayTime
                .dayPreference(UPDATED_DAY_PREFERENCE);

        restDayTimeMockMvc.perform(put("/api/day-times")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedDayTime)))
                .andExpect(status().isOk());

        // Validate the DayTime in the database
        List<DayTime> dayTimes = dayTimeRepository.findAll();
        assertThat(dayTimes).hasSize(databaseSizeBeforeUpdate);
        DayTime testDayTime = dayTimes.get(dayTimes.size() - 1);
        assertThat(testDayTime.getDayPreference()).isEqualTo(UPDATED_DAY_PREFERENCE);
    }

    @Test
    @Transactional
    public void deleteDayTime() throws Exception {
        // Initialize the database
        dayTimeRepository.saveAndFlush(dayTime);
        int databaseSizeBeforeDelete = dayTimeRepository.findAll().size();

        // Get the dayTime
        restDayTimeMockMvc.perform(delete("/api/day-times/{id}", dayTime.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DayTime> dayTimes = dayTimeRepository.findAll();
        assertThat(dayTimes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
