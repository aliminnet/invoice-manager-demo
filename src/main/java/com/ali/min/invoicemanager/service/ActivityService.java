package com.ali.min.invoicemanager.service;

import com.ali.min.invoicemanager.dto.ActivityDTO;
import com.ali.min.invoicemanager.dto.InvoiceDTO;
import com.ali.min.invoicemanager.enums.Status;
import com.ali.min.invoicemanager.mapper.ActivityMapper;
import com.ali.min.invoicemanager.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    /**
        * This method is used to save activity
        * @param invoiceDTO
        * @param status
        * @return activity
        */
    public void saveActivity(InvoiceDTO invoiceDTO, Status status) {
        ActivityDTO activityDTO = new ActivityDTO();
        //map invoiceDTO to activityDTO
        activityDTO.setAmount(invoiceDTO.getAmount());
        activityDTO.setEmail(invoiceDTO.getEmail());
        activityDTO.setFirstName(invoiceDTO.getFirstName());
        activityDTO.setLastName(invoiceDTO.getLastName());
        activityDTO.setStatus(status);
        activityDTO.setBillNo(invoiceDTO.getBillNo());
        activityDTO.setActivityDate(LocalDateTime.now());
        activityRepository.save(activityMapper.toEntity(activityDTO));
    }


    /**
     * This method is used to get all activities by status
     * @param status
     * @return List of activities
     */
    public List<ActivityDTO> getActivitiesByStatus(Status status) {
        return activityRepository.findAllByStatus(status).stream().map(activityMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * This method is used to get all activities
     * @return List of activities
     */
    public List<ActivityDTO> getActivities() {
        return activityRepository.findAll().stream().map(activityMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * This method is used to get activity by id
     * @param id
     * @return activity
     */
    public ActivityDTO getActivityById(Long id) {
        return activityRepository.findById(id).map(activityMapper::toDTO).orElseThrow(() -> new IllegalArgumentException("Activity not found"));
    }
}
