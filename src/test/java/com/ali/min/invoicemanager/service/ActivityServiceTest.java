package com.ali.min.invoicemanager.service;

import com.ali.min.invoicemanager.dto.ActivityDTO;
import com.ali.min.invoicemanager.dto.InvoiceDTO;
import com.ali.min.invoicemanager.entity.ActivityEntity;
import com.ali.min.invoicemanager.enums.Status;
import com.ali.min.invoicemanager.mapper.ActivityMapper;
import com.ali.min.invoicemanager.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private ActivityMapper activityMapper;

    @InjectMocks
    private ActivityService activityService;

    private InvoiceDTO invoiceDTO;
    private ActivityDTO activityDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        invoiceDTO = new InvoiceDTO();
        invoiceDTO.setAmount(new BigDecimal("150"));
        invoiceDTO.setEmail("test@example.com");
        invoiceDTO.setFirstName("John");
        invoiceDTO.setLastName("Doe");
        invoiceDTO.setBillNo("12345");

        activityDTO = new ActivityDTO();
        activityDTO.setAmount(new BigDecimal("150"));
        activityDTO.setEmail("test@example.com");
        activityDTO.setFirstName("John");
        activityDTO.setLastName("Doe");
        activityDTO.setStatus(Status.SUCCESS);
        activityDTO.setBillNo("12345");
        activityDTO.setActivityDate(LocalDateTime.now());
    }

    @Test
    void saveActivity_savesActivitySuccessfully() {
        when(activityMapper.toEntity(any(ActivityDTO.class))).thenReturn(new ActivityEntity());
        when(activityRepository.save(any(ActivityEntity.class))).thenReturn(new ActivityEntity());

        activityService.saveActivity(invoiceDTO, Status.SUCCESS);

        verify(activityRepository).save(any(ActivityEntity.class));
    }
    @Test
    void getActivitiesByStatus_returnsActivitiesSuccessfully() {
        when(activityRepository.findAllByStatus(any(Status.class))).thenReturn(Collections.singletonList(new ActivityEntity()));
        when(activityMapper.toDTO(any(ActivityEntity.class))).thenReturn(activityDTO);

        List<ActivityDTO> result = activityService.getActivitiesByStatus(Status.SUCCESS);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(activityDTO.getEmail(), result.get(0).getEmail());
        verify(activityRepository).findAllByStatus(Status.SUCCESS);
    }

    @Test
    void getActivities_returnsAllActivitiesSuccessfully() {
        when(activityRepository.findAll()).thenReturn(Collections.singletonList(new ActivityEntity()));
        when(activityMapper.toDTO(any(ActivityEntity.class))).thenReturn(activityDTO);

        List<ActivityDTO> result = activityService.getActivities();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(activityDTO.getEmail(), result.get(0).getEmail());
        verify(activityRepository).findAll();
    }


    @Test
    void getActivityById_returnsActivitySuccessfully() {
        when(activityRepository.findById(anyLong())).thenReturn(Optional.of(new ActivityEntity()));
        when(activityMapper.toDTO(any(ActivityEntity.class))).thenReturn(activityDTO);

        ActivityDTO result = activityService.getActivityById(1L);

        assertNotNull(result);
        assertEquals(activityDTO.getEmail(), result.getEmail());
        verify(activityRepository).findById(1L);
    }

    @Test
    void getActivityById_throwsExceptionWhenActivityNotFound() {
        when(activityRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> activityService.getActivityById(1L));
    }
}
